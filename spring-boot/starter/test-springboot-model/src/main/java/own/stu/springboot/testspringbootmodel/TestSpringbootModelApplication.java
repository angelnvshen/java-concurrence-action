package own.stu.springboot.testspringbootmodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ows.stu.springboot.simplespringbootstarterautoconfigurer.config.HelloAutoConfiguration;

@SpringBootApplication
public class TestSpringbootModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSpringbootModelApplication.class, args);
	}

}
