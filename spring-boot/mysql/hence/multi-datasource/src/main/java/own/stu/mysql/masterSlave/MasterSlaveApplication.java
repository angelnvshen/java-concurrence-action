package own.stu.mysql.masterSlave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class MasterSlaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterSlaveApplication.class, args);
    }

}
