package own.stu.netty.rpcsim.sample.server;

import own.stu.netty.rpcsim.sample.api.HelloService;
import own.stu.netty.rpcsim.sample.api.Person;
import own.stu.netty.rpcsim.server.RpcService;

@RpcService(value = HelloService.class, version = "sample.hello2")
public class HelloServiceImpl2 implements HelloService {

    @Override
    public String hello(String name) {
        return "你好! " + name;
    }

    @Override
    public String hello(Person person) {
        return "你好! " + person.getFirstName() + " " + person.getLastName();
    }
}