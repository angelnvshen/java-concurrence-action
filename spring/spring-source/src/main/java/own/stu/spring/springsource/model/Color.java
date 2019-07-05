package own.stu.spring.springsource.model;

import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Color {

  @Value("10发文件")
  private String desc1;

  @Value("${name}")
  private String desc2;

  @Value("#{10-2}")
  private Integer desc3;

  @Value("#{'${numList}'.split(',')}")
  private List<Integer> numList;
}