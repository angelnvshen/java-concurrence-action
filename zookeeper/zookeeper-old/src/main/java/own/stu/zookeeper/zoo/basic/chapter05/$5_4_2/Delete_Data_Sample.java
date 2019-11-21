package own.stu.zookeeper.zoo.basic.chapter05.$5_4_2;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

public class Delete_Data_Sample {

    static String path = "/zk-book/c1";

    static CuratorFramework client = CuratorFrameworkFactory.builder().
            connectString(ZK_Constants.url).
            connectionTimeoutMs(5000).
            sessionTimeoutMs(3000).
            retryPolicy(new ExponentialBackoffRetry(1000, 3)).
            build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());
        Stat sta = new Stat();
        System.out.println(new String(client.getData().storingStatIn(sta).forPath(path)));
        System.out.println(sta);
        client.delete().deletingChildrenIfNeeded().withVersion(sta.getVersion()).forPath(path);
    }
}
