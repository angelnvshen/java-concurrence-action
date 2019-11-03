package own.stu.redis.oneMaster;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import own.stu.redis.oneMaster.config.RestTemplateConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class GetAPI {

//    private TestRestTemplate template = new TestRestTemplate();
    RestTemplate template = null;

    {

        RestTemplateConfig con = new RestTemplateConfig();
        template = con.httpRestTemplate();
    }

    @Test
    public void testGet() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Resource> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<byte[]> response = template.exchange("http://chuantu.xyz/t6/703/1572707371x2362407012.jpg", HttpMethod.GET,
                httpEntity, byte[].class);
        log.info("===状态码================");
        log.info(">> {}", response.getStatusCodeValue());
        log.info("===返回信息================");
        log.info(">> {}", response.getHeaders().getContentType());
        log.info(">> {}", response.getHeaders().getContentType().getSubtype());
        try {
            File file = File.createTempFile("ess-", "." + response.getHeaders().getContentType().getSubtype());
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(response.getBody());
            fos.flush();
            fos.close();
            System.out.println(file.getAbsolutePath());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @After
    public void testAfter() {
        template = null;
    }

}