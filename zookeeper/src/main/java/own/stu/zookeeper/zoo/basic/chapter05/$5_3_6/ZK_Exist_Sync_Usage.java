package own.stu.zookeeper.zoo.basic.chapter05.$5_3_6;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZK_Exist_Sync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper(ZK_Constants.url, 5000, new ZK_Exist_Sync_Usage());
        System.out.println(zooKeeper.getState());
        connectedSemaphore.await();
        System.out.println("zk session established");

        String path = "/zk-book";

        Stat stat = zooKeeper.exists(path, true);
        System.out.println("stat : " + stat);

        zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zooKeeper.setData(path, "hello, beau".getBytes() , -1);

        zooKeeper.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zooKeeper.setData(path + "/c1", "hello, gi".getBytes() , -1);

        zooKeeper.delete(path + "/c1" , -1);

        zooKeeper.delete(path, -1);

        TimeUnit.SECONDS.sleep(300);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Received event :" + watchedEvent);
        try {

            if(Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                connectedSemaphore.countDown();
            }else if(watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                System.out.println("Node(" + watchedEvent.getPath() + ") : NodeDataChanged ");
                zooKeeper.exists(watchedEvent.getPath(), true);
            }else if(watchedEvent.getType() == Event.EventType.NodeCreated){
                System.out.println("Node(" + watchedEvent.getPath() + ") : NodeCreated ");
                zooKeeper.exists(watchedEvent.getPath(), true);
            }else if(watchedEvent.getType() == Event.EventType.NodeDeleted){
                System.out.println("Node(" + watchedEvent.getPath() + ") : NodeDeleted ");
                zooKeeper.exists(watchedEvent.getPath(), true);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
