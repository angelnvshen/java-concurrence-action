package own.stu.netty.rpcsim.sample.server;

import own.stu.netty.rpcsim.sample.api.HelloService;
import own.stu.netty.rpcsim.sample.api.Person;
import own.stu.netty.rpcsim.server.RpcService;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }

    @Override
    public String hello(Person person) {
        return "Hello! " + person.getFirstName() + " " + person.getLastName();
    }
}