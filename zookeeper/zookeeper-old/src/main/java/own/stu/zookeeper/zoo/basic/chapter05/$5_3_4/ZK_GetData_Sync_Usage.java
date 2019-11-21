package own.stu.zookeeper.zoo.basic.chapter05.$5_3_4;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZK_GetData_Sync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private static ZooKeeper zooKeeper = null;

    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper(ZK_Constants.url, 5000, new ZK_GetData_Sync_Usage());
        System.out.println(zooKeeper.getState());
        connectedSemaphore.await();
        System.out.println("zk session established");
        String path = "/zk-book";
        zooKeeper.create(path, "hello it's me".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println(new String(zooKeeper.getData(path, true, stat)));
        System.out.println(stat.getCzxid() + ", " + stat.getMzxid() + ", " + stat.getVersion());

        zooKeeper.setData(path, "i am from canifnia ".getBytes(), -1);

        TimeUnit.SECONDS.sleep(300);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Received event :" + watchedEvent);
        if(KeeperState.SyncConnected == watchedEvent.getState()){
            if(EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                connectedSemaphore.countDown();
            }else if(watchedEvent.getType() == EventType.NodeDataChanged) {
                try {
                    System.out.println(new String(zooKeeper.getData(watchedEvent.getPath(), true, stat)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(stat.getCzxid() + ", " + stat.getMzxid() + ", " + stat.getVersion());
            }
        }
    }
}
