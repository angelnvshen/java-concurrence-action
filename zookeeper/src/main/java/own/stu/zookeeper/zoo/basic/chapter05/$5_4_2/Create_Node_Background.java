package own.stu.zookeeper.zoo.basic.chapter05.$5_4_2;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Create_Node_Background {

    static String path = "/zk-book";

    static CuratorFramework client = CuratorFrameworkFactory.builder().
            sessionTimeoutMs(5000).
            connectString(ZK_Constants.url).
            connectionTimeoutMs(3000).
            retryPolicy(new ExponentialBackoffRetry(1000, 3)).
            build();

    static ExecutorService service = Executors.newFixedThreadPool(2);

    static CountDownLatch count = new CountDownLatch(2);

    public static void main(String[] args) throws Exception {
        client.start();
        System.out.println("Main thread : " + Thread.currentThread().getName());

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println("Event [ code : " + event.getResultCode() + ", type : " + event.getType() + " ]");
                System.out.println("Thread of processResult : " + Thread.currentThread().getName());

                count.countDown();
            }
        }, service).forPath(path, "hello".getBytes());

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println("Event [ code : " + event.getResultCode() + ", type : " + event.getType() + " ]");
                System.out.println("Thread of processResult : " + Thread.currentThread().getName());

                count.countDown();
            }
        }).forPath(path, "hello".getBytes());

        count.await();

        service.shutdown();
    }
}
