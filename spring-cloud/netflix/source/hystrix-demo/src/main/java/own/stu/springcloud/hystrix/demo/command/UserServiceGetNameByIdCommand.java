package own.stu.springcloud.hystrix.demo.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import own.stu.springcloud.hystrix.demo.service.UserService;

public class UserServiceGetNameByIdCommand extends HystrixCommand<String> {

    private UserService userService;

    private Integer userId;

    public UserServiceGetNameByIdCommand(UserService userService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userServiceCommandGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("getNameByIdCommand")));
        this.userService = userService;
    }

    @Override
    protected String run() throws Exception {
        return userService.getNameById(userId);
    }

    @Override
    protected String getFallback() {
        return "张三 - default(fallback) ";
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
