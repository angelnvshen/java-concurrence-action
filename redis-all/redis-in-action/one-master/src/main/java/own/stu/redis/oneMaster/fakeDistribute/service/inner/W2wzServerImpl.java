package own.stu.redis.oneMaster.fakeDistribute.service.inner;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;

public class W2wzServerImpl implements DistributedServer {

    static String code = "W2WZ";

    @Override
    public String getServer() {
        return "http://1.w2wz.com/upload.php";
    }

    @Override
    public String dealBodyAfterSendFilePart(String body) {
        Assert.notNull(body, "body is null");
        String xx = "URL: (<a href=\"";
        int index = body.indexOf("URL: (<a href=\"");
        String substring = body.substring(index + xx.length());
        int index1 = substring.indexOf("\"");
        return substring.substring(0, index1);
    }

    public HttpEntity<MultiValueMap<String, Object>> getHttpEntity(File file) {
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);

        //设置请求体，注意是LinkedMultiValueMap
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("uploadimg", fileSystemResource);
        form.add("filename", file.getName());

        //用HttpEntity封装整个请求报文 , HttpEntity<MultiValueMap<String, Object>> files =
        return new HttpEntity<>(form, headers);
    }
}
