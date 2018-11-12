package own.stu.tkmybatis.demo.common.util;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MimeType;
import org.springframework.web.client.RestTemplate;

public class TCaptchaVerify {
    private static final String APP_ID = "2055559515";
    private static final String APP_SECRET = "0oUjfuoqM7P56tXw-BV7YQA**";
    private static final String VERIFY_URI = "https://ssl.captcha.qq.com/ticket/verify";
    private static final String VERIFY_URI_WITH_PARAM = "https://ssl.captcha.qq.com/ticket/verify?aid=%s&AppSecretKey=%s&Ticket=%s&Randstr=%s&UserIP=%s";

    public static int verifyTicket(String ticket, String rand, String userIp) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(APPLICATION_JSON_UTF8, MediaType.valueOf("text/json")));
        restTemplate.setMessageConverters(Collections.singletonList(converter));
        Map<String, String> params = new HashMap<>();
        params.put("aid", APP_ID);
        params.put("AppSecretKey", APP_SECRET);
        params.put("Ticket", ticket);
        params.put("Randstr", rand);
        params.put("UserIP", userIp);
        //返回格式 text/json
        ResponseEntity<WaterWallResponseEntity> responseEntity =
            restTemplate.getForEntity(VERIFY_URI, WaterWallResponseEntity.class, params);
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
        }
    }

    public static void main(String[] args) throws Exception {
        verifyTicket("112", "111", "127.0.0.1");
    }
}