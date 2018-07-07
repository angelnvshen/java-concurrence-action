package own.stu.zookeeper.zoo.basic.chapter05.$5_3_4;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZK_GetData_ASync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private static ZooKeeper zooKeeper = null;

    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper(ZK_Constants.url, 5000, new ZK_GetData_ASync_Usage());
        System.out.println(zooKeeper.getState());
        connectedSemaphore.await();
        System.out.println("zk session established");
        String path = "/zk-book";
        zooKeeper.create(path, "hello it's me".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zooKeeper.getData(path, true, new IDataCallBack(), null);

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
                    zooKeeper.getData(watchedEvent.getPath(), true, new IDataCallBack(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class IDataCallBack implements AsyncCallback.DataCallback{

    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        System.out.println("GetData : [ rc : " + rc + ", path : " + path + ", ctx : " + ctx + ", data : " + new String(data) + "] " );
        System.out.println(stat.getCzxid() + ", " + stat.getMzxid() + ", " + stat.getVersion());
    }
}
