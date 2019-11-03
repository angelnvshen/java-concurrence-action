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
import own.stu.redis.oneMaster.fakeDistribute.service.inner.W2wzServerImpl;
import own.stu.redis.oneMaster.fakeDistribute.util.FileUtil;

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


    private int default_file_part_size = 300 * 1024;

    static List<DistributedServer> serverList = Lists.newArrayList(new W2wzServerImpl());

    public String distribute(String fileName) {

        // need todo 策略模式

        List<String> partFileNameList = splitFileService.splitBySize(fileName, default_file_part_size);

        List<FileInfo.FilePartInfo> filePartInfoList = new ArrayList<>();
        // TODO parallel ensure order for merge
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap();
        partFileNameList.parallelStream().forEach(s -> {
            String remoteFilePartPath = upAndDownService.sendFilePartToRemote(
                    restTemplate, serverList.get(0).getServer(), new File(s));
            map.put(s, remoteFilePartPath);
        });

        partFileNameList.forEach(s -> filePartInfoList.add(new FileInfo.FilePartInfo(s, map.get(s))));
        deleteIfExisted(partFileNameList);

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(getSimpleFileName(fileName));
        fileInfo.setFilePartInfoList(filePartInfoList);
        return getSeed(fileInfo);
    }

    private String getSeed(FileInfo fileInfo) {

        String fileJson = JSON.toJSONString(fileInfo);
        String jsonFileName = writeTempFile(fileInfo.getFileName(), fileJson.getBytes());

        String remote = upAndDownService.sendFilePartToRemote(
                restTemplate, serverList.get(0).getServer(), new File(jsonFileName));

        deleteIfExisted(jsonFileName);
        System.out.println(jsonFileName);
        return remote;
    }

    public void downloadFile(String fileSeed) throws IOException {
        Assert.notNull(fileSeed, "fileSeed is null");

        UpAndDownService.RemoteSendState<String> state = upAndDownService.getFileFromRemote(restTemplate, fileSeed, String.class);
        if (HttpStatus.OK.value() != state.getCode()) {
            throw new RuntimeException(state.getBody());
        }

        String stateBody = state.getBody();

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
        splitFileService.mergePartFiles(FileUtil.currentWorkDir, ".jpg", default_file_part_size, "111.jpg");
    }

    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        RestTemplateConfig con = new RestTemplateConfig();
        RestTemplate restTemplate = con.httpRestTemplate();

        QuickSplitFileService splitFileService = new QuickSplitFileService(poolExecutor);
        UpAndDownService upAndDownService = new UpAndDownService(restTemplate);
        DistributeService distributeService = new DistributeService(restTemplate, splitFileService, upAndDownService);

//        distributeService.distribute("/Users/my/Downloads/redis-pdfdownloads.rar");

//        distributeService.distribute("/Users/my/Desktop/111.jpg");
//        String xx = "{\"fileName\":\"/Users/my/Desktop/111.jpg\",\"filePartInfoList\":[{\"partFileOrderName\":\"111.jpg.1.jpg\",\"remotePartFileName\":\"http://chuantu.xyz/t6/703/1572707371x2362407012.jpg\"},{\"partFileOrderName\":\"111.jpg.2.jpg\",\"remotePartFileName\":\"http://chuantu.xyz/t6/703/1572707377x3752237043.jpg\"},{\"partFileOrderName\":\"111.jpg.3.jpg\",\"remotePartFileName\":\"http://chuantu.xyz/t6/703/1572707378x2362407012.jpg\"}]}";


        /*String url = "http://chuantu.xyz/t6/703/1572707371x2362407012.jpg";
        UpAndDownService.RemoteSendState<byte[]> fileFromRemote = upAndDownService.getFileFromRemote(restTemplate, url, byte[].class);

        */

        distributeService.downloadFile("http://chuantu.xyz/t6/703/1572754033x1033347913.jpg");

        poolExecutor.shutdown();

    }
}
