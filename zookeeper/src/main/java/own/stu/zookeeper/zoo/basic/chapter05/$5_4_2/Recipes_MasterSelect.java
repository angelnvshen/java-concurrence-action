package own.stu.zookeeper.zoo.basic.chapter05.$5_4_2;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.util.concurrent.TimeUnit;

public class Recipes_MasterSelect {

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

        LeaderSelector selector = new LeaderSelector(client, path, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("this is master .");

                TimeUnit.SECONDS.sleep(2);

                System.out.println("release the master .");
            }

        });

        selector.autoRequeue();
        selector.start();

        TimeUnit.SECONDS.sleep(100);
    }
}
