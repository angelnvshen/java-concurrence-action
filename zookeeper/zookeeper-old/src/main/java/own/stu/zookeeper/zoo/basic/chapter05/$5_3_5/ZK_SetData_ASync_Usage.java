package own.stu.zookeeper.zoo.basic.chapter05.$5_3_5;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZK_SetData_ASync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper(ZK_Constants.url, 5000, new ZK_SetData_ASync_Usage());
        System.out.println(zooKeeper.getState());
        connectedSemaphore.await();
        System.out.println("zk session established");

        String path = "/zk-book";
        zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zooKeeper.setData(path, "hello , beau".getBytes(), -1, new IStatCallBack(), null);

        TimeUnit.SECONDS.sleep(300);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Received event :" + watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            connectedSemaphore.countDown();
        }
    }
}

class IStatCallBack implements AsyncCallback.StatCallback{

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if(rc == 0) {
            System.out.println("SUCCESS");
        }
    }
}
