package own.stu.zookeeper.zoo.basic.chapter05.$5_3_3;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZK_Delete_Sync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(ZK_Constants.url,
                5000,
                new ZK_Delete_Sync_Usage()
                );
        connectedSemaphore.await();

        String p = "/zk-test-ephemeral-";
        String path = zooKeeper.create(p , "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("create path : " + path);

        String path2 = zooKeeper.create(p,
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("create path : " + path2);

        List<String> path_children = zooKeeper.getChildren("/", null);
        for(String s : path_children)
            System.out.println(" ====== " + s);

        Stat p_node = zooKeeper.exists(path, null);
        System.out.println("path exist : " + p_node);

        zooKeeper.delete(path, 0);

        p_node = zooKeeper.exists(path, null);
        System.out.println("path exist : " + p_node);

        path_children = zooKeeper.getChildren("/", null);
        for(String s : path_children)
            System.out.println(" ====== " + s);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Received event :" + watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            connectedSemaphore.countDown();
        }
    }
}
