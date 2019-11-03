package own.stu.redis.oneMaster.fakeDistribute.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import own.stu.redis.oneMaster.fakeDistribute.service.inner.DistributedServer;
import own.stu.redis.oneMaster.fakeDistribute.service.inner.W2wzServerImpl;
import own.stu.redis.oneMaster.fakeDistribute.util.FileUtil;

import java.io.File;

@Service
public class UpAndDownService {

    private RestTemplate restTemplate;

    public UpAndDownService(@Qualifier("httpRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static String sendFilePartToRemote(RestTemplate restTemplate, DistributedServer server, File file) {

        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> files = server.getHttpEntity(file);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(server.getServer(), files, String.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            // todo need try backUp server
            throw new RuntimeException(responseEntity.getBody());
        }

        FileUtil.deleteIfExisted(file);

        return server.dealBodyAfterSendFilePart(responseEntity.getBody());
    }

    public static <T> RemoteSendState getFileFromRemote(RestTemplate restTemplate, String url, Class<T> tClass) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36");
        HttpEntity<Resource> entity = new HttpEntity<>(headers);

        ResponseEntity<T> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, tClass);

        return new RemoteSendState<>(exchange.getStatusCodeValue(), exchange.getBody());
    }

    public static void main(String[] args) {

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
