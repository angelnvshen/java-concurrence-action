package own.stu.zookeeper.zoo.basic.chapter05.$5_3_4;

import org.apache.zookeeper.*;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;
import org.apache.zookeeper.Watcher.Event.*;
import org.apache.zookeeper.ZooDefs.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZK_GetChildren_Sync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper(ZK_Constants.url, 5000, new ZK_GetChildren_Sync_Usage());
        System.out.println(zooKeeper.getState());
        connectedSemaphore.await();
        System.out.println("zk session established");
        String path = "/zk-book";
        zooKeeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create(path + "/c1", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        List<String> childrenList = zooKeeper.getChildren(path, true);
        System.out.println(childrenList);

        zooKeeper.create(path + "/c2", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        TimeUnit.SECONDS.sleep(300);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Received event :" + watchedEvent);
        if(KeeperState.SyncConnected == watchedEvent.getState()){
            if(EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                connectedSemaphore.countDown();
            }else if(watchedEvent.getType() == EventType.NodeChildrenChanged) {
                try {
                    System.out.println("ReGetChildren : " + zooKeeper.getChildren(watchedEvent.getPath(), true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
