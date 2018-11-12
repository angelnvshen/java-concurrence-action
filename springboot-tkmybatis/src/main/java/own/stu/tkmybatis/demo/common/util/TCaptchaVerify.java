package own.stu.tkmybatis.demo.common.util;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class TCaptchaVerify {
    private static final String APP_ID = "2055559515";
    private static final String APP_SECRET = "0oUjfuoqM7P56tXw-BV7YQA**";
    private static final String VERIFY_URI = "https://ssl.captcha.qq.com/ticket/verify";
    private static final String VERIFY_URI_WITH_PARAM = "https://ssl.captcha.qq.com/ticket/verify?aid=%s&AppSecretKey=%s&Ticket=%s&Randstr=%s&UserIP=%s";

    public static int verifyTicket(String ticket, String rand, String userIp) {

        try{
        String url = String.format(VERIFY_URI_WITH_PARAM, APP_ID, APP_SECRET, ticket, rand, userIp);

        RestTemplate restTemplate = getRestTemplate();

        //返回格式 text/json
        ResponseEntity<WaterWallResponseEntity> responseEntity =
            restTemplate.getForEntity(url, WaterWallResponseEntity.class, new HashMap<>());
        if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
            WaterWallResponseEntity data = responseEntity.getBody();
            if(data.getResponse() == 1){
                return 1;
            }else {
                System.out.println(data.getErr_msg());
                return -1;
            }
        }else{
            System.out.println("服务器连接失败");
            return -1;
        }}catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    private static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(APPLICATION_JSON_UTF8, MediaType.valueOf("text/json")));
        restTemplate.setMessageConverters(Collections.singletonList(converter));
        return restTemplate;
    }

    public static class WaterWallResponseEntity implements Serializable {
        private int response;
        private int evil_level;
        private String err_msg;

        public int getResponse() {
            return response;
        }

        public void setResponse(int response) {
            this.response = response;
        }

        public int getEvil_level() {
            return evil_level;
        }

        public void setEvil_level(int evil_level) {
            this.evil_level = evil_level;
        }

        public String getErr_msg() {
            return err_msg;
        }

        public void setErr_msg(String err_msg) {
            this.err_msg = err_msg;
        }

        public WaterWallResponseEntity() {
        }
    }
}