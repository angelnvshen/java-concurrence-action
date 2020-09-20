package own.stu.netty.rpcsim.sample.api;

public interface HelloService {

    String hello(String name);

    String hello(Person person);
}