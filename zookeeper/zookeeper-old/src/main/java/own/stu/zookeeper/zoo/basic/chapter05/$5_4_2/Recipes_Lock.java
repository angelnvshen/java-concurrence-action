package own.stu.zookeeper.zoo.basic.chapter05.$5_4_2;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class Recipes_Lock {

    static String lock_path = "/curator_recipes_lock_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder().
            sessionTimeoutMs(5000).
            connectString(ZK_Constants.url).
            connectionTimeoutMs(3000).
            retryPolicy(new ExponentialBackoffRetry(1000, 3)).
            build();

    public static void main(String[] args) {

        client.start();

        final InterProcessMutex lock = new InterProcessMutex(client, lock_path);

        final CountDownLatch count = new CountDownLatch(1);
        for(int i=0;i<30; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        count.await();
                        lock.acquire();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = format.format(new Date());
                    System.out.println("order is : " + orderNo);

                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            count.countDown();
        }
    }
}
