package own.stu.redis.oneMaster.fakeDistribute.service;

import com.alibaba.fastjson.JSON;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import own.stu.redis.oneMaster.fakeDistribute.model.FileInfo;
import own.stu.redis.oneMaster.fakeDistribute.service.inner.DistributedServer;
import own.stu.redis.oneMaster.fakeDistribute.service.inner.W2wzServerImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class DistributeService {

    @Autowired
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

    public String distribute(String fileName) {

        // need todo 策略模式
        List<DistributedServer> serverList = Lists.newArrayList(new W2wzServerImpl());
        List<String> partFileNameList = splitFileService.splitBySize(fileName, default_file_part_size);

        List<FileInfo.FilePartInfo> filePartInfoList = new ArrayList<>();
        // TODO parallel ensure order for merge
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap();
        partFileNameList.parallelStream().forEach(s -> {
            String remoteFilePartPath = upAndDownService.restTemplateTransferFile(
                    restTemplate, serverList.get(0).getServer(), new File(s));
            map.put(s, remoteFilePartPath);
        });

        partFileNameList.forEach(s -> filePartInfoList.add(new FileInfo.FilePartInfo(s, map.get(s))));
        deleteIfExisted(partFileNameList);

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(fileName);
        fileInfo.setFilePartInfoList(filePartInfoList);
        return JSON.toJSONString(fileInfo);
    }

    // TODO json -> seed

    public void downloadFile(String fileSeed) throws IOException {
        Assert.notNull(fileSeed, "fileSeed is null");

        UpAndDownService.RemoteSendState<String> state = upAndDownService.getFile(restTemplate, fileSeed);
        if (HttpStatus.OK.value() != state.getCode()) {
            throw new RuntimeException(state.getBody());
        }

        String stateBody = state.getBody();
        FileInfo fileInfo = JSON.parseObject(stateBody, FileInfo.class);
        List<FileInfo.FilePartInfo> filePartInfoList = fileInfo.getFilePartInfoList();
        filePartInfoList.stream().forEach(filePartInfo -> {
            UpAndDownService.RemoteSendState<String> file = upAndDownService.getFile(restTemplate, filePartInfo.getRemotePartFileName());
            if (HttpStatus.OK.value() != state.getCode()) {
                return; // failOver
            }

            try {
                OutputStream os = new FileOutputStream(fileInfo.getFileName() + "/" + filePartInfo.getPartFileOrderName());
                byte[] b = new byte[file.getBody().length()];
                os.write(b);
                os.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        splitFileService.mergePartFiles("", ".part", filePartInfoList.size(), fileInfo.getFileName());
    }

    /*public static void main(String[] args) throws IOException {

        OutputStream outputStream = new ByteArrayOutputStream();
        Resources.copy(new URL("http://chuantu.xyz/t6/703/1572696241x3752237043.jpg"), outputStream);
        byte[] bytes = new byte[1024];
        outputStream.write(bytes);
        System.out.println(new String(bytes));
    }*/

    /*public static void main(String[] args) throws IOException {
        String fileStr = UUID.randomUUID() + ".part";
        FileOutputStream stream = new FileOutputStream(fileStr);
        stream.write("ASDFGHJKL".getBytes());
        stream.flush();
//        stream.getFD()
        stream.close();

        File file = new File(fileStr);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists())
            file.delete();// 文件删除
    }*/

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory = new OkHttp3ClientHttpRequestFactory();
        okHttp3ClientHttpRequestFactory.setConnectTimeout(120);
        RestTemplate restTemplate = new RestTemplate(okHttp3ClientHttpRequestFactory);

        QuickSplitFileService splitFileService = new QuickSplitFileService(poolExecutor);
        UpAndDownService upAndDownService = new UpAndDownService(restTemplate);
        DistributeService distributeService = new DistributeService(restTemplate, splitFileService, upAndDownService);

//        distributeService.distribute("/Users/my/Downloads/redis-pdfdownloads.rar");
        distributeService.distribute("/Users/my/Desktop/111.jpg");
        String xx = "{\"fileName\":\"/Users/my/Desktop/111.jpg\",\"filePartInfoList\":[{\"partFileOrderName\":\"111.jpg.1.jpg\",\"remotePartFileName\":\"http://chuantu.xyz/t6/703/1572707371x2362407012.jpg\"},{\"partFileOrderName\":\"111.jpg.2.jpg\",\"remotePartFileName\":\"http://chuantu.xyz/t6/703/1572707377x3752237043.jpg\"},{\"partFileOrderName\":\"111.jpg.3.jpg\",\"remotePartFileName\":\"http://chuantu.xyz/t6/703/1572707378x2362407012.jpg\"}]}";
        poolExecutor.shutdown();
    }

    private void deleteIfExisted(List<String> fileName) {
        for (String s : fileName) {
            File file = new File(s);
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists())
                file.delete();// 文件删除
        }
    }
}
