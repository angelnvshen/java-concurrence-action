package own.stu.netty.rpcsim.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import own.stu.netty.rpcsim.registry.ServiceRegistry;
import own.stu.netty.rpcsim.registry.zkImpl.ZooKeeperServiceRegistry;
import own.stu.netty.rpcsim.server.RpcServer;

@Configuration
public class BeanLoadConfig {

    @Bean
    public ServiceRegistry getZooKeeperServiceRegistry(Environment environment) {
        ZooKeeperServiceRegistry zooKeeperServiceRegistry = new ZooKeeperServiceRegistry(environment.getProperty("rpc.registry_address"));
        return zooKeeperServiceRegistry;
    }

    @Bean
    public RpcServer getRpcServer(ServiceRegistry serviceRegistry, Environment environment) {
        String serverAddress = environment.getProperty("rpc.service_address");
        RpcServer rpcServer = new RpcServer(serverAddress, serviceRegistry);
        return rpcServer;
    }

}
