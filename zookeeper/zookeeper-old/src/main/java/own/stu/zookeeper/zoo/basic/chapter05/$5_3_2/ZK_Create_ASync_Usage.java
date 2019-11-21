package own.stu.zookeeper.zoo.basic.chapter05.$5_3_2;

import org.apache.zookeeper.*;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZK_Create_ASync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(ZK_Constants.url,
                5000,
                new ZK_Create_ASync_Usage()
                );
        connectedSemaphore.await();

        zooKeeper.create("/zk-test-ephemeral-",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                new IStringCallback(),
                "I am hello world ."
                );

        zooKeeper.create("/zk-test-ephemeral-",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                new IStringCallback(),
                "I am hello world ."
        );

        zooKeeper.create("/zk-test-ephemeral-",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL,
                new IStringCallback(),
                "I am hello world ."
        );
        TimeUnit.SECONDS.sleep(100);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Received event :" + watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            connectedSemaphore.countDown();
        }
    }

}
class IStringCallback implements AsyncCallback.StringCallback{

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println("create path result : [" + rc + ", " + path + ", " + ctx + ", real path name : " + name + "]");
    }
}