package own.stu.tkmybatis.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaTray;
import org.junit.Test;
import org.springframework.http.MediaType;
import own.stu.tkmybatis.demo.common.util.TCaptchaVerify.WaterWallResponseEntity;

public class PureTest {

  @Test
  public void test() throws IOException {

    MediaType mediaType = MediaType.valueOf("text/json");
    System.out.println(mediaType);
  }
}
