package own.stu.zookeeper.zoo.basic.chapter05.$5_4_2;

import org.apache.curator.RetryLoop;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.util.concurrent.TimeUnit;

public class Create_Session_Sample {

    public static void main(String[] args) throws InterruptedException {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 4);

        CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_Constants.url, 5000, 3000, retryPolicy);

        client.start();

        TimeUnit.SECONDS.sleep(100);
    }
}
