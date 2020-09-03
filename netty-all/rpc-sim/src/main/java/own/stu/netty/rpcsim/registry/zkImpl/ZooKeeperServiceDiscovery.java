package own.stu.netty.rpcsim.registry.zkImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.util.CollectionUtils;
import own.stu.netty.rpcsim.registry.ServiceDiscovery;
import own.stu.netty.rpcsim.registry.zkImpl.curator.CuratorClientFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class ZooKeeperServiceDiscovery implements ServiceDiscovery {

    private String zkAddress;

    public ZooKeeperServiceDiscovery(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    @Override
    public String discover(String serviceName) {

        // 创建 ZooKeeper 客户端
        CuratorFramework zkClient = CuratorClientFactory.INSTANCE.getClient(zkAddress);
        log.debug("connect zookeeper");

        try {
            // 获取 service 节点
            String servicePath = Constant.ZK_REGISTRY_PATH + "/" + serviceName;
            if (zkClient.checkExists().forPath(servicePath) == null) {
                throw new RuntimeException(String.format("can not find any service node on path: %s", servicePath));
            }

            List<String> addressList = zkClient.getChildren().forPath(servicePath);
            if (CollectionUtils.isEmpty(addressList)) {
                throw new RuntimeException(String.format("can not find any address node on path: %s", servicePath));
            }

            // 获取 address 节点
            String address;
            int size = addressList.size();
            if (size == 1) {
                // 若只有一个地址，则获取该地址
                address = addressList.get(0);
                log.debug("get only address node: {}", address);
            } else {
                // 若存在多个地址，则随机获取一个地址
                address = addressList.get(ThreadLocalRandom.current().nextInt(size));
                log.debug("get random address node: {}", address);
            }

            // 获取 address 节点的值
            String addressPath = servicePath + "/" + address;
            return new String(zkClient.getData().forPath(addressPath));

        } catch (Exception e) {
            e.printStackTrace();
            log.error("discover {} error {}", serviceName, e.getMessage());
            return "";
        } finally {
            zkClient.close();
        }
    }
}
