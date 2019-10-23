package own.stu.springcloud.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadPoolConfig {
    @Bean
    public ExecutorService getExecutorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        return executorService;
    }
}
