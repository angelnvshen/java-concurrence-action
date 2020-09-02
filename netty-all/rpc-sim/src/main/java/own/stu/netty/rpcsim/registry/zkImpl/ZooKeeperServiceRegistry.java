package own.stu.netty.rpcsim.registry.zkImpl;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import own.stu.netty.rpcsim.registry.ServiceRegistry;

/**
 * 基于 ZooKeeper 的服务注册接口实现
 */
@Slf4j
public class ZooKeeperServiceRegistry implements ServiceRegistry {

    private final ZkClient zkClient;
//    private CuratorFramework zkClient;

    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

    public ZooKeeperServiceRegistry(String zkAddress) {
        // 创建 ZooKeeper 客户端
        this.zkClient = new ZkClient(zkAddress, Constant.ZK_SESSION_TIMEOUT, Constant.ZK_CONNECTION_TIMEOUT);
        /*this.zkClient = CuratorFrameworkFactory.builder()
                .connectString(zkAddress)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(Constant.ZK_SESSION_TIMEOUT)
                .connectionTimeoutMs(Constant.ZK_CONNECTION_TIMEOUT)
                .build();*/

        log.debug("connect zookeeper");
    }

    @Override
    public void register(String serviceName, String serviceAddress) {

        // 创建 registry 节点（持久）
        String registryPath = Constant.ZK_REGISTRY_PATH;
        if (!zkClient.exists(registryPath)){
            zkClient.createPersistent(registryPath);
            log.debug("create registry node: {}", registryPath);
        }

        // 创建 service 节点（持久）
        String servicePath = registryPath + "/" + serviceName;
        if (!zkClient.exists(servicePath)) {
            zkClient.createPersistent(servicePath);
            log.debug("create service node: {}", servicePath);
        }

        // 创建 address 节点（临时）
        String addressPath = servicePath + "/address-";
        String addressNode = zkClient.createEphemeralSequential(addressPath, serviceAddress);
        log.debug("create address node: {}", addressNode);
    }
}
