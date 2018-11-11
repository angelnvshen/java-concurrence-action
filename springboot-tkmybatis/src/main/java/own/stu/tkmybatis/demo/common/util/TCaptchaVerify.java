package own.stu.tkmybatis.demo.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.net.URLEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class TCaptchaVerify {
    private static final String APP_ID = "2055559515";
    private static final String APP_SECRET = "0oUjfuoqM7P56tXw-BV7YQA**";
    private static final String VERIFY_URI = "https://ssl.captcha.qq.com/ticket/verify?aid=%s&AppSecretKey=%s&Ticket=%s&Randstr=%s&UserIP=%s";

    public static int verifyTicket(String ticket, String rand, String userIp) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet;
        CloseableHttpResponse response = null;
        try {
            httpGet = new HttpGet(String.format(VERIFY_URI,
                    APP_ID,
                    APP_SECRET,
                    URLEncoder.encode(ticket, "UTF-8"),
                    URLEncoder.encode(rand, "UTF-8"),
                    URLEncoder.encode(userIp, "UTF-8")
            ));
            response = httpclient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String res = EntityUtils.toString(entity);
                 System.out.println(res); // 临时输出

                JSONObject result = JSON.parseObject(res);
                // 返回码
                int code = result.getInteger("response");
                // 恶意等级
                int evilLevel = result.getInteger("evil_level");

                // 验证成功
              if (code == 1) {
//                return evilLevel;
                return code;
              }
            }
        } catch (java.io.IOException e) {
            // 忽略
        } finally {
            try {
                response.close();
            } catch (Exception ignore) {
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        verifyTicket("112", "111", "127.0.0.1");
    }
}