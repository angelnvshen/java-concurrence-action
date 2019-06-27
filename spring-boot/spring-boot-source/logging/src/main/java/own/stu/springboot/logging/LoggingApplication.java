package own.stu.springboot.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingApplication {

  public static void main(String[] args) {
    SpringApplication.run(LoggingApplication.class, args);
  }

}
