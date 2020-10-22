package own.stu.springcloud.hystrix.demo;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.junit.Test;
import own.stu.springcloud.hystrix.demo.command.UserServiceGetNameByIdCommand;
import own.stu.springcloud.hystrix.demo.service.UserService;
import own.stu.springcloud.hystrix.demo.service.UserServiceImpl;

public class DemoTest {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        UserServiceGetNameByIdCommand command = new UserServiceGetNameByIdCommand(userService);
        command.setUserId(1);
        try {
            for (int i = 0; i < 2; i++) {
                String userName = command.execute();
                System.out.println(userName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
