package own.stu.shardingjdbc.shardingjdbc.simple;

import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

@SpringBootApplication(
        exclude = {
        }
)
public class ShardingjdbcSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingjdbcSimpleApplication.class, args);
    }

}
