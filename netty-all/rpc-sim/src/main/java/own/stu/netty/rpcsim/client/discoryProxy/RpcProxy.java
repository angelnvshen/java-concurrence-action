package own.stu.netty.rpcsim.client.discoryProxy;

import lombok.extern.slf4j.Slf4j;
import own.stu.netty.rpcsim.client.remote.NettyRemoteManager;
import own.stu.netty.rpcsim.registry.ServiceDiscovery;
import own.stu.netty.rpcsim.registry.zkImpl.ZooKeeperServiceDiscovery;

import java.lang.reflect.Proxy;

@Slf4j
public class RpcProxy {

    private String discoveryServiceAddress;

    private ServiceDiscovery serviceDiscovery;

    public RpcProxy(String discoveryServiceAddress) {
        this.discoveryServiceAddress = discoveryServiceAddress;
        this.serviceDiscovery = new ZooKeeperServiceDiscovery(discoveryServiceAddress);
    }

    public RpcProxy(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(final Class<T> interfaceClass) {
        return create(interfaceClass, "");
    }

    @SuppressWarnings("unchecked")
    public <T> T create(final Class<T> interfaceClass, final String serviceVersion) {
        // 创建动态代理对象
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new ObjectInvocationHandler<>(interfaceClass, serviceVersion, serviceDiscovery)
        );
    }

    public void stop() {
        serviceDiscovery.clear();
        NettyRemoteManager.getInstance().stop();
    }
}