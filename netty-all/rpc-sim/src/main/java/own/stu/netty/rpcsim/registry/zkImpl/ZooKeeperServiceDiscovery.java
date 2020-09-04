package own.stu.netty.rpcsim.registry.zkImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.springframework.util.CollectionUtils;
import own.stu.netty.rpcsim.client.remote.NettyRemoteManager;
import own.stu.netty.rpcsim.registry.ServiceDiscovery;
import own.stu.netty.rpcsim.registry.zkImpl.curator.CuratorClient;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ZooKeeperServiceDiscovery implements ServiceDiscovery {

    private String zkAddress;

    private CuratorClient zkClient;

    private Map<String, List<String>> addressMap = new ConcurrentHashMap<>();// serviceName : list<serviceAddr>

    public ZooKeeperServiceDiscovery(String zkAddress) {
        this.zkAddress = zkAddress;
        // 创建 ZooKeeper 客户端
        this.zkClient = new CuratorClient(zkAddress);
        log.debug("connect zookeeper");
        init();
    }

    private void init() {
        try {
            List<String> serviceList = zkClient.getChildren(Constant.ZK_REGISTRY_PATH);
            String servicePath = null;
            Set<String> serviceAddrSet = new HashSet<>();
            for (String service : serviceList) {
                servicePath = Constant.ZK_REGISTRY_PATH + "/" + service;
                List<String> addressList = zkClient.getChildren(servicePath);
                List<String> serviceAddrList = addressMap.get(service);
                if (serviceAddrList == null) {
                    serviceAddrList = new CopyOnWriteArrayList<>();
                    addressMap.put(service, serviceAddrList);
                }

                for (String addr : addressList) {
                    String serviceAddr = new String(zkClient.getData(servicePath + "/" + addr));
                    serviceAddrList.add(serviceAddr);
                    serviceAddrSet.add(serviceAddr);
                }
            }
            for (String addr : serviceAddrSet) {
                String[] strs = addr.split(":");
                assert strs != null && strs.length == 2;
                NettyRemoteManager.getInstance().clearServerNode(addr);
                NettyRemoteManager.getInstance().connectServerNode(strs[0], Integer.valueOf(strs[1]));
            }
        } catch (Exception e) {
            log.error("Get node exception: " + e.getMessage());
        }
    }

    public static void main2(String[] args) throws Exception {
        CuratorClient client = new CuratorClient("192.168.0.128:2181");
        List<String> children = client.getChildren(Constant.ZK_REGISTRY_PATH);
        System.out.println(children);
        String childPath = null;
        for (String child : children) {
            childPath = Constant.ZK_REGISTRY_PATH + "/" + child;
            List<String> addressList = client.getChildren(childPath);
            for (String addr : addressList) {
                System.out.println(new String(client.getData(childPath + "/" + addr)));
            }
        }
        client.watchPathChildrenNode("/registry/own.stu.netty.rpcsim.sample.api.HelloService", (client1, event) -> {
            PathChildrenCacheEvent.Type type = event.getType();
            System.out.println(new String(event.getData().getData()));
            switch (type) {
                case CONNECTION_RECONNECTED:
                    log.info("Reconnected to zk, try to get latest service list");
                    break;
                case CHILD_ADDED:
                    log.info("Service info added, try to get latest service list");
                    break;
                case CHILD_UPDATED:
                    log.info("Service info updated, try to get latest service list");
                    break;
                case CHILD_REMOVED:
                    log.info("Service info removed, try to get latest service list");
                    break;
            }
        });
        TimeUnit.HOURS.sleep(1);
    }

    @Override
    public String discover(String serviceName) {

        try {
            // 获取 service 节点
            String servicePath = Constant.ZK_REGISTRY_PATH + "/" + serviceName;
            if (!zkClient.pathExists(servicePath)) {
                throw new RuntimeException(String.format("can not find any service node on path: %s", servicePath));
            }

            List<String> addressList = addressMap.get(serviceName);
            if (!CollectionUtils.isEmpty(addressList)) {
                return randomRobin(addressList);
            }
            addressList = zkClient.getChildren(servicePath);
            if (CollectionUtils.isEmpty(addressList)) {
                throw new RuntimeException(String.format("can not find any address node on path: %s", servicePath));
            }

            // 获取 address 节点
            String address = randomRobin(addressList);

            // 获取 address 节点的值
            String addressPath = servicePath + "/" + address;
            return new String(zkClient.getData(addressPath));

        } catch (Exception e) {
            e.printStackTrace();
            log.error("discover {} error {}", serviceName, e.getMessage());
            return "";
        }
    }

    private String randomRobin(List<String> list) {
        String result;
        int size = list.size();
        if (size == 1) {
            // 若只有一个地址，则获取该地址
            result = list.get(0);
            log.debug("get only result node: {}", result);
        } else {
            // 若存在多个地址，则随机获取一个地址
            result = list.get(ThreadLocalRandom.current().nextInt(size));
            log.debug("get random result node: {}", result);
        }
        return result;
    }

    @Override
    public void clear() {
        zkClient.close();
    }
}
