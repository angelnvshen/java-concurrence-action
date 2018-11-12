package own.stu.tkmybatis.demo;

import java.io.IOException;
import org.junit.Test;
import org.springframework.http.MediaType;

public class PureTest {

  @Test
  public void test() throws IOException {

    MediaType mediaType = MediaType.valueOf("text/json");
    System.out.println(mediaType);

//    Randstr=%254042x&UserIP=0%253A0%253A0%253A0%253A0%253A0%253A0%253A1
//    Randstr=%4042x&UserIP=0%3A0%3A0%3A0%3A0%3A0%3A0%3A1
  }
}
