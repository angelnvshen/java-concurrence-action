package own.stu.zookeeper.zoo.basic.chapter05.$5_4_2;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.util.concurrent.TimeUnit;

public class PathChildrenCache_Sample {

    static String path = "/zk-book";

    static CuratorFramework client = CuratorFrameworkFactory.builder().
            sessionTimeoutMs(5000).
            connectString(ZK_Constants.url).
            connectionTimeoutMs(3000).
            retryPolicy(new ExponentialBackoffRetry(1000, 3)).
            build();

    public static void main(String[] args) throws Exception {
        client.start();
        System.out.println("Main thread : " + Thread.currentThread().getName());

        PathChildrenCache childrenCache = new PathChildrenCache(client, path, true);

        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()){
                    case CHILD_ADDED:
                        System.out.println("CHILD ADD : " + event.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("CHILD UPDATE : " + event.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("CHILD REMOVED : " + event.getData().getPath());
                        break;
                    default:
                        break;
                }
            }
        });

        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, "hill".getBytes());
        TimeUnit.SECONDS.sleep(1);

        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path+"/c1", "hill".getBytes());
        TimeUnit.SECONDS.sleep(1);

        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path+"/c1/c2", "hill".getBytes());
        TimeUnit.SECONDS.sleep(1);

        client.delete().deletingChildrenIfNeeded().forPath(path);

        TimeUnit.SECONDS.sleep(100);
    }
}
