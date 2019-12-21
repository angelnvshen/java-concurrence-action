package own.stu.learn.rocketwithtcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class RocketWithTccApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketWithTccApplication.class, args);
    }

    @Bean
    public ExecutorService executorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        return executorService;
    }

}
