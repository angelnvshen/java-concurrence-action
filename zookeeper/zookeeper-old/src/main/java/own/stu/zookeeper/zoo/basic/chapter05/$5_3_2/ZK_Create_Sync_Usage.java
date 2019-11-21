package own.stu.zookeeper.zoo.basic.chapter05.$5_3_2;

import org.apache.zookeeper.*;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZK_Create_Sync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(ZK_Constants.url,
                5000,
                new ZK_Create_Sync_Usage()
                );
        connectedSemaphore.await();

        String path = zooKeeper.create("/zk-test-ephemeral-", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("create path : " + path);

        String path2 = zooKeeper.create("/zk-test-ephemeral-",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("create path : " + path2);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Received event :" + watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            connectedSemaphore.countDown();
        }
    }
}
