package own.stu.tkmybatis.demo.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TCaptchaVerify {
    private static final String APP_ID = "2055559515";
    private static final String APP_SECRET = "0oUjfuoqM7P56tXw-BV7YQA**";
    private static final String VERIFY_URI = "https://ssl.captcha.qq.com/ticket/verify";
    private static final String VERIFY_URI_WITH_PARAM = "https://ssl.captcha.qq.com/ticket/verify?aid=%s&AppSecretKey=%s&Ticket=%s&Randstr=%s&UserIP=%s";

    public static int verifyTicket(String ticket, String rand, String userIp) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("aid", APP_ID);
        params.put("AppSecretKey", APP_SECRET);
        params.put("Ticket", ticket);
        params.put("Randstr", rand);
        params.put("UserIP", userIp);
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

    static class WaterWallResponseEntity{
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
    }
}