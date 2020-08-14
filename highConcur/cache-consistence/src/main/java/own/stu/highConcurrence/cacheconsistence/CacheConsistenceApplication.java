package own.stu.highConcurrence.cacheconsistence;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("own.stu.highConcurrence.cacheconsistence.dao")
public class CacheConsistenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheConsistenceApplication.class, args);
	}

}
