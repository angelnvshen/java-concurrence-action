package own.stu.netty.rpcsim.registry.zkImpl.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import own.stu.netty.rpcsim.registry.zkImpl.Constant;

public class CuratorClientFactory {

    public static final CuratorClientFactory INSTANCE = new CuratorClientFactory();

    public CuratorFramework getClient(String zkAddress) {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        // 创建 ZooKeeper 客户端
        CuratorFramework build = CuratorFrameworkFactory.builder()
                .connectString(zkAddress)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(Constant.ZK_SESSION_TIMEOUT)
                .connectionTimeoutMs(Constant.ZK_CONNECTION_TIMEOUT)
                .build();
        build.start();

        return build;
    }
}
