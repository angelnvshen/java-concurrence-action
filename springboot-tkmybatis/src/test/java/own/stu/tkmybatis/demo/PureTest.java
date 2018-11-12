package own.stu.tkmybatis.demo;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import own.stu.tkmybatis.demo.common.util.TCaptchaVerify;
import own.stu.tkmybatis.demo.common.util.TCaptchaVerify.WaterWallResponseEntity;

public class PureTest {

  @Before
  public void before(){

  }

  @Test
  public void test() throws IOException {

    MediaType mediaType = MediaType.valueOf("text/json");
    System.out.println(mediaType);

    RestTemplate restTemplate = TCaptchaVerify.getRestTemplate();
    Map<String, String> params = new HashMap<>();
    params.put("aid", "2055559515");
    params.put("AppSecretKey", "0oUjfuoqM7P56tXw-BV7YQA**");
    params.put("Ticket",  "t02wyqjfvA2jhKwH6Jj3kB5DgABepnbek_gMfVWgMDvzDv6Twkitduxgpb5JPfHKWkl04N2eMy-7vd2v9mC9yXoTDHNMbf5Gn_MnSdsFavdY3-9H36H5Viv9g**");
    params.put("Randstr", "%4042x");
    params.put("UserIP", "0%3A0%3A0%3A0%3A0%3A0%3A0%3A1");

    ResponseEntity<WaterWallResponseEntity> responseEntity =
        restTemplate.getForEntity("https://ssl.captcha.qq.com/ticket/verify", WaterWallResponseEntity.class, params);
    if(responseEntity.getStatusCode().equals(HttpStatus.OK)) {
      WaterWallResponseEntity data = responseEntity.getBody();
      System.out.println(data);
    }
  }

  @Test
  public void test2() throws IOException {
    RestTemplate restTemplate = TCaptchaVerify.getRestTemplate();
    String url = "https://ssl.captcha.qq.com/ticket/verify?aid=2055559515&AppSecretKey=0oUjfuoqM7P56tXw-BV7YQA**&Ticket=t02wyqjfvA2jhKwH6Jj3kB5DgABepnbek_gMfVWgMDvzDv6Twkitduxgpb5JPfHKWkl04N2eMy-7vd2v9mC9yXoTDHNMbf5Gn_MnSdsFavdY3-9H36H5Viv9g**&Randstr=%4042x&UserIP=0%3A0%3A0%3A0%3A0%3A0%3A0%3A1";
    WaterWallResponseEntity responseEntity = restTemplate.getForObject(url, WaterWallResponseEntity.class);
    System.out.println(responseEntity);
  }
}
