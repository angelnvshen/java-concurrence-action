package own.stu.netty.rpcsim.sample.client;

import own.stu.netty.rpcsim.client.RpcProxy;
import own.stu.netty.rpcsim.registry.zkImpl.Constant;
import own.stu.netty.rpcsim.registry.zkImpl.ZooKeeperServiceDiscovery;
import own.stu.netty.rpcsim.sample.api.HelloService;

public class HelloClient {

    public static void main(String[] args) throws Exception {

        ZooKeeperServiceDiscovery discovery = new ZooKeeperServiceDiscovery(Constant.ADDRESS);
        RpcProxy rpcProxy = new RpcProxy(discovery);

        HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.hello("World");
        System.out.println(result);

        HelloService helloService2 = rpcProxy.create(HelloService.class, "sample.hello2");
        String result2 = helloService2.hello("世界");
        System.out.println(result2);
    }
}