package own.stu.netty.rpcsim.registry.zkImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.CollectionUtils;
import own.stu.netty.rpcsim.registry.ServiceRegistry;
import own.stu.netty.rpcsim.registry.zkImpl.curator.CuratorClientFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于 ZooKeeper 的服务注册接口实现
 */
@Slf4j
public class ZooKeeperServiceRegistry implements ServiceRegistry {

    private CuratorFramework zkClient;

    private Map<String, Set<String>> map;

    public ZooKeeperServiceRegistry(String zkAddress) {
        // 创建 ZooKeeper 客户端
        this.zkClient = CuratorClientFactory.INSTANCE.getClient(zkAddress);
        map = new ConcurrentHashMap<>();
        log.debug("connect zookeeper");
    }

    @Override
    public void register(String serviceName, String serviceAddress) {

        try {

            // 创建 registry 节点（持久）
            String registryPath = Constant.ZK_REGISTRY_PATH;

            Stat stat = zkClient.checkExists().forPath(registryPath);
            if (stat == null) {
                zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT)
                        .forPath(registryPath);
                log.debug("create registry node: {}", registryPath);
            }

            // 创建 service 节点（持久）
            String servicePath = registryPath + "/" + serviceName;
            stat = zkClient.checkExists().forPath(servicePath);
            if (stat == null) {
                zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT)
                        .forPath(servicePath);
                log.debug("create service node: {}", servicePath);
            }

            // 创建 address 节点（临时）
            String addressPath = servicePath + "/address-";
            stat = zkClient.checkExists().forPath(addressPath);
            if (stat == null) {
                String addressNode = zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT)
                        .forPath(addressPath, serviceAddress.getBytes());

                log.debug("create address node: {}", addressNode);
            }
            /*serviceNameToCache(addressPath, serviceAddress);
            registerEphemeralAddress(addressPath, serviceAddress);*/

        } catch (Exception e) {
            e.printStackTrace();
            log.error("register serviceName {} in {} , {}", serviceName, serviceAddress, e.getMessage());
        }
    }

    private void serviceNameToCache(String serviceNamePath, String serviceAddress) {
        Set<String> serviceNames = map.get(serviceAddress);
        if (serviceNames == null) {
            serviceNames = new HashSet<>();
            map.put(serviceAddress, serviceNames);
        }
        serviceNames.add(serviceNamePath);
    }

    public void registerAllEphemeralServiceNameTo(String serviceAddress) throws Exception {
        Set<String> serviceNames = map.get(serviceAddress);
        if (CollectionUtils.isEmpty(serviceNames)) {
            return;
        }
        for (String serviceName : serviceNames) {
            registerEphemeralAddress(serviceName, serviceAddress);
        }
    }

    private void registerEphemeralAddress(String serviceNamePath, String serviceAddress) throws Exception {
        Stat stat = zkClient.checkExists().forPath(serviceNamePath);
        if (stat == null) {
            String addressNode = zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(serviceNamePath, serviceAddress.getBytes());

            log.debug("create address node: {}", addressNode);
        }
    }
}
