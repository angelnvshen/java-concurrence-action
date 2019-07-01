package own.stu.springboot.hello.autoconfigurer.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "own.hello")
public class Hello {

  String prefix;

  String suffix;
}
