package own.stu.zookeeper.zoo.basic.chapter05.$5_4_2;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Node_Cache {

    static String path = "/zk-book/cache";

    static CuratorFramework client = CuratorFrameworkFactory.builder().
            sessionTimeoutMs(5000).
            connectString(ZK_Constants.url).
            connectionTimeoutMs(3000).
            retryPolicy(new ExponentialBackoffRetry(1000, 3)).
            build();

    public static void main(String[] args) throws Exception {
        client.start();
        System.out.println("Main thread : " + Thread.currentThread().getName());

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "hello".getBytes());

        final NodeCache nodeCache = new NodeCache(client, path, false);
        nodeCache.start();
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("node data update, new data ：" + new String(nodeCache.getCurrentData().getData()));
            }
        });

        client.setData().forPath(path, "what".getBytes());

        TimeUnit.SECONDS.sleep(1);

        client.delete().deletingChildrenIfNeeded().forPath(path);

        TimeUnit.SECONDS.sleep(100);
    }
}
