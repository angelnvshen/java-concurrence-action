package own.stu.redis.oneMaster.fakeDistribute.service;

import com.alibaba.fastjson.JSON;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;
import own.stu.redis.oneMaster.config.RestTemplateConfig;
import own.stu.redis.oneMaster.fakeDistribute.model.FileInfo;
import own.stu.redis.oneMaster.fakeDistribute.service.inner.DistributedServer;
import own.stu.redis.oneMaster.fakeDistribute.service.inner.LocalServerImpl;
import own.stu.redis.oneMaster.fakeDistribute.service.inner.W2wzServerImpl;
import own.stu.redis.oneMaster.fakeDistribute.util.FileUtil;
import own.stu.redis.oneMaster.fakeDistribute.util.SplitPartSize;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static own.stu.redis.oneMaster.fakeDistribute.util.FileUtil.*;

@Service
public class DistributeService {

    @Resource(name = "httpRestTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private QuickSplitFileService splitFileService;

    @Autowired
    private UpAndDownService upAndDownService;

    DistributeService() {
    }

    DistributeService(RestTemplate restTemplate, QuickSplitFileService splitFileService, UpAndDownService upAndDownService) {
        this.restTemplate = restTemplate;
        this.splitFileService = splitFileService;
        this.upAndDownService = upAndDownService;
    }

    static List<DistributedServer> serverList = Lists.newArrayList(new LocalServerImpl(), new W2wzServerImpl());

    public String distribute(String fileName) {

        // need todo 策略模式
        List<String> partFileNameList = splitFileService.splitBySize(fileName);

        List<FileInfo.FilePartInfo> filePartInfoList = new ArrayList<>();
        // TODO parallel ensure order for merge
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap();
        partFileNameList.parallelStream().forEach(s -> {
//        partFileNameList.stream().forEach(s -> {
            String remoteFilePartPath = upAndDownService.sendFilePartToRemote(
                    restTemplate, serverList.get(0), new File(s));
            map.put(s, remoteFilePartPath);
        });

        partFileNameList.forEach(s -> filePartInfoList.add(new FileInfo.FilePartInfo(s, map.get(s))));

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(getSimpleFileName(fileName));
        fileInfo.setFilePartInfoList(filePartInfoList);
        fileInfo.setFileSize(new File(fileName).length());
        return getSeed(fileInfo);
    }

    private String getSeed(FileInfo fileInfo) {

        String fileJson = JSON.toJSONString(fileInfo);
        String jsonFileName = writeTempFile(fileInfo.getFileName(), fileJson.getBytes());

        String remote = upAndDownService.sendFilePartToRemote(
                restTemplate, serverList.get(0), new File(jsonFileName));

        deleteIfExisted(jsonFileName);
        return remote;
    }

    public void downloadFile(String fileSeed) throws IOException {
        Assert.notNull(fileSeed, "fileSeed is null");

        UpAndDownService.RemoteSendState<byte[]> state = upAndDownService.getFileFromRemote(restTemplate, fileSeed, byte[].class);
        if (HttpStatus.OK.value() != state.getCode()) {
            throw new RuntimeException(new String(state.getBody()));
        }

        String stateBody = new String(state.getBody());

        FileInfo fileInfo = JSON.parseObject(stateBody, FileInfo.class);
        List<FileInfo.FilePartInfo> filePartInfoList = fileInfo.getFilePartInfoList();
        filePartInfoList.stream().forEach(filePartInfo -> {
            UpAndDownService.RemoteSendState<byte[]> file = upAndDownService.getFileFromRemote(restTemplate, filePartInfo.getRemotePartFileName(), byte[].class);
            if (HttpStatus.OK.value() != file.getCode()) {
                return; // failOver
            }

            try (
                    OutputStream os = new FileOutputStream(filePartInfo.getPartFileOrderName())
            ) {
                FileCopyUtils.copy(file.getBody(), os);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        int default_file_part_size = SplitPartSize.filePartSize(fileInfo.getFileSize());
        splitFileService.mergePartFiles(FileUtil.currentWorkDir, ".jpg", default_file_part_size, fileInfo.getFileName());
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);

        RestTemplateConfig con = new RestTemplateConfig();
        RestTemplate restTemplate = con.httpRestTemplate();

        QuickSplitFileService splitFileService = new QuickSplitFileService(poolExecutor);
        UpAndDownService upAndDownService = new UpAndDownService(restTemplate);
        DistributeService distributeService = new DistributeService(restTemplate, splitFileService, upAndDownService);

        try {

//        String seed = distributeService.distribute("/Users/my/Downloads/04并发编程的原理(下)- 【www.zxit8.com】.mp4");
//            String seed = distributeService.distribute("/Users/my/Desktop/大图网-下载说明.jpg");
//            System.out.println(seed);


            distributeService.downloadFile("http://localhost:8080/distribute/download?filename=04并发编程的原理(下)- 【www.zxit8.com】3799856882541700614.mp4");
            System.out.println("cost: ==== " + (System.currentTimeMillis() - start));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolExecutor.shutdown();
        }


    }
}
