package own.stu.redis.oneMaster.fakeDistribute.service;

import lombok.Data;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import own.stu.redis.oneMaster.fakeDistribute.service.inner.W2wzServerImpl;

import java.io.File;

@Service
public class UpAndDownService {

    private RestTemplate restTemplate;

    public UpAndDownService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static String restTemplateTransferFile(RestTemplate restTemplate, String server, File file) {

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);

        //设置请求体，注意是LinkedMultiValueMap
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("uploadimg", fileSystemResource);
        form.add("filename", file.getName());

        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(server, files, String.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            // todo need try backUp server
            throw new RuntimeException(responseEntity.getBody());
        }

        W2wzServerImpl w2wzServer = new W2wzServerImpl();
        return w2wzServer.dealBody(responseEntity.getBody());
    }

    public static RemoteSendState getFile(RestTemplate restTemplate, String url) {

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return new RemoteSendState<>(exchange.getStatusCodeValue(), exchange.getBody());
    }

    @Data
    public static class RemoteSendState<T> {
        private T body;
        private Integer code;

        public RemoteSendState(Integer code, T body) {
            this.body = body;
            this.code = code;
        }
    }
}
