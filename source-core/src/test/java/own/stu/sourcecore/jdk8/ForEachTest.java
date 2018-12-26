package own.stu.sourcecore.jdk8;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class ForEachTest {

  @Test
  public void test(){
    List<String> list = Arrays.asList("1", "3", "3", "2");
    list.forEach((t) ->{
      if(t.compareTo("2") > -1){
        System.out.println(t);
      }
    });
  }
}
