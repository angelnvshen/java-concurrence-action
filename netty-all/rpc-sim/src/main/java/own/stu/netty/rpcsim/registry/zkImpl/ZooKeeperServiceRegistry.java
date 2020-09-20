package own.stu.netty.rpcsim.registry.zkImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.springframework.util.CollectionUtils;
import own.stu.netty.rpcsim.registry.ServiceRegistry;
import own.stu.netty.rpcsim.registry.zkImpl.curator.CuratorClient;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于 ZooKeeper 的服务注册接口实现
 */
@Slf4j
public class ZooKeeperServiceRegistry implements ServiceRegistry {

    private CuratorClient zkClient;

    private Map<String, Set<String>> map; // serviceAddress : set<serviceName>

    public ZooKeeperServiceRegistry(String zkAddress) {
        // 创建 ZooKeeper 客户端
        this.zkClient = new CuratorClient(zkAddress);

        map = new ConcurrentHashMap<>();

        log.debug("connect zookeeper");

        initSpaceNodeCheck();
    }

    private void initSpaceNodeCheck() {
        try {
            // 创建 registry 节点（持久）
            String registryPath = Constant.ZK_REGISTRY_PATH;

            if (!zkClient.pathExists(registryPath)) {
                zkClient.createPathData(registryPath, null);
                log.debug("create registry node: {}", registryPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ZooKeeperServiceRegistry initCheck error, {}", e.getMessage());
        }
    }

    @Override
    public void register(Set<String> serviceNameSet, String serviceAddress) {

        serviceNameSet.stream().forEach((serviceName) -> register(serviceName, serviceAddress));

        zkClient.addConnectionStateListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                if (connectionState == ConnectionState.RECONNECTED) {
                    log.info("Connection state: {}, register service after reconnected", connectionState);
                    try {
                        registerAllServiceAddressEphemeral(serviceAddress);
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("reRegister registerAllServiceAddressEphemeral in {} , error: {}", serviceAddress, e.getMessage());
                    }
                }
            }
        });
    }

    @Override
    public void register(String serviceName, String serviceAddress) {

        try {
            // 创建 service 节点（持久）
            String servicePath = Constant.ZK_REGISTRY_PATH + "/" + serviceName;

            if (!zkClient.pathExists(servicePath)) {
                zkClient.createPathData(servicePath, null);
                log.debug("create service node: {}", servicePath);
            }

            // 创建 address 节点（临时）
            registerServiceAddressEphemeral(servicePath, serviceAddress);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("register serviceName {} in {} , {}", serviceName, serviceAddress, e.getMessage());
        }
    }

    private void registerAllServiceAddressEphemeral(String serviceAddress) throws Exception {
        Set<String> serviceNames = map.get(serviceAddress);
        if (CollectionUtils.isEmpty(serviceNames)) {
            return;
        }
        synchronized (serviceAddress) {
            for (String serviceName : serviceNames) {
                registerServiceAddressEphemeral(serviceName, serviceAddress);
            }
        }
    }

    private void registerServiceAddressEphemeral(String servicePath, String serviceAddress) throws Exception {
        String addressPath = servicePath + "/address-";
        serviceNameToCache(servicePath, serviceAddress);
        registerEphemeralAddress(addressPath, serviceAddress);
    }

    private void serviceNameToCache(String serviceNamePath, String serviceAddress) {
        Set<String> serviceNames = map.get(serviceAddress);
        if (serviceNames == null) {
            serviceNames = new HashSet<>();
            map.put(serviceAddress, serviceNames);
        }
        serviceNames.add(serviceNamePath);
    }

    private void registerEphemeralAddress(String serviceNamePath, String serviceAddress) throws Exception {
        if (!zkClient.pathExists(serviceNamePath)) {
            String addressNode = zkClient.createEphemeralSequentialPathData(
                    serviceNamePath, serviceAddress.getBytes());
            log.debug("create address node: {}", addressNode);
        }
    }
}
