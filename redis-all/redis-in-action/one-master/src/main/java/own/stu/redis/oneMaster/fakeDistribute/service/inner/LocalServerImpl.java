package own.stu.redis.oneMaster.fakeDistribute.service.inner;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;

public class LocalServerImpl implements DistributedServer{

    @Override
    public String getServer() {
        return "http://localhost:8080/distribute/upload/";
    }

    @Override
    public String dealBodyAfterSendFilePart(String body) {
        // You successfully uploaded file=1572707371x2362407012.jpg
        String markStr = "You successfully uploaded file=";

        return "http://localhost:8080/distribute/download?filename=" + body.substring(markStr.length());
    }

    public HttpEntity<MultiValueMap<String, Object>> getHttpEntity(File file) {
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);

        //设置请求体，注意是LinkedMultiValueMap
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", fileSystemResource);
        form.add("filename", file.getName());

        //用HttpEntity封装整个请求报文 , HttpEntity<MultiValueMap<String, Object>> files =
        return new HttpEntity<>(form, headers);
    }
}
