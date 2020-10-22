package own.stu.springcloud.hystrix.demo.service;

public class UserServiceImpl implements UserService {
    @Override
    public String getNameById(Integer id) {
//        int i = 10/0;
        return "hello world";
    }
}
