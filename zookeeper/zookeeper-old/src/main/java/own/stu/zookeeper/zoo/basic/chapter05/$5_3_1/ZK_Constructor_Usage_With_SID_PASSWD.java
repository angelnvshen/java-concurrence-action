package own.stu.zookeeper.zoo.basic.chapter05.$5_3_1;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZK_Constructor_Usage_With_SID_PASSWD implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper(ZK_Constants.url, 5000, new ZK_Constructor_Usage_With_SID_PASSWD());
        System.out.println(zooKeeper.getState());
        connectedSemaphore.await();
        System.out.println("zk session established");
        long sessionId = zooKeeper.getSessionId();
        byte[] passwd = zooKeeper.getSessionPasswd();
        zooKeeper = new ZooKeeper(ZK_Constants.url,
                5000,
                new ZK_Constructor_Usage_With_SID_PASSWD(),
                1L,
                "test".getBytes()
        );

        zooKeeper = new ZooKeeper(ZK_Constants.url,
                5000,
                new ZK_Constructor_Usage_With_SID_PASSWD(),
                sessionId,
                passwd
        );

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Received event :" + watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            connectedSemaphore.countDown();
        }
    }
}
