package own.stu.zookeeper.review;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OwnTest {
    @Test
    public void testSingleServer() throws IOException, InterruptedException, KeeperException {
        Zookeeper_Constructor_Usage watcher = new Zookeeper_Constructor_Usage();
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, watcher);
        System.out.println(zooKeeper.getState());
        watcher.getConnectionLatch().await();
        System.out.println("established connection");

        System.out.println(" =============== ");

        // sync 同步创建节点
        String node = zooKeeper.create("/hello", "world".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("success create node : " + node);

        System.out.println(" =============== ");

        // async 异步创建节点
        zooKeeper.create("/hello2", "zoo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, new IStringCallBack(), "na ni ? ");

        System.out.println(" =============== ");

        Stat stat = new Stat();
        System.out.println(new String(zooKeeper.getData("/hello", false, stat)));
        System.out.println(stat);

        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * 临时节点的有效性，session_timeout 之内
     */
    @Test
    public void testNodeWhenSessionTimeout_part_one() throws IOException, InterruptedException, KeeperException {

        Zookeeper_Constructor_Usage watcher = new Zookeeper_Constructor_Usage();
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 60000, watcher);
        watcher.getConnectionLatch().await();
        System.out.println("established connection");
        System.out.println(zooKeeper.getSessionId() + " - " + new String(zooKeeper.getSessionPasswd()));

        System.out.println(" =============== ");

        String node = zooKeeper.create("/hello-tmp-node-2", "world".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("success create node : " + node);

    }

    @Test
    public void testNodeWhenSessionTimeout_part_two() throws IOException, InterruptedException, KeeperException {
        long sessionId = 72090290047877130L;
        String sessionPassword = "��U��k�E\u0019\f\"�s�ǩ";
        Zookeeper_Constructor_Usage watcher = new Zookeeper_Constructor_Usage();
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 60000, watcher, sessionId, sessionPassword.getBytes(), false);

        watcher.getConnectionLatch().await();
        System.out.println("established connection");
        System.out.println(" ================== ");

        Stat stat = new Stat();
        System.out.println(new String(zooKeeper.getData("/hello-tmp-node-2", false, stat)));
        System.out.println(stat);
    }

    @Test
    public void testSync() throws IOException, InterruptedException {

    }

    @Test
    public void testNodeTmp() throws IOException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++)
            executorService.submit(() -> {
                Zookeeper_Constructor_Usage watcher = new Zookeeper_Constructor_Usage();
                try {
                    ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 60000, watcher);
                    watcher.getConnectionLatch().await();
                    System.out.println("established connection");
                    zooKeeper.create("/seq-node", "node-".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL, new IStringCallBack(), "seq-node");

                    System.out.println(" ============ ");
                /*Stat stat = new Stat();
                zooKeeper.getData("/seq-node", false, stat);
                System.out.println(stat);*/

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        TimeUnit.SECONDS.sleep(10);


//        zooKeeper.sync();
    }

    static class Zookeeper_Constructor_Usage implements Watcher {

        private CountDownLatch connectionLatch = new CountDownLatch(1);

        public CountDownLatch getConnectionLatch() {
            return connectionLatch;
        }

        @Override
        public void process(WatchedEvent event) {
            System.out.println("received watched event : " + event);
            if (Event.KeeperState.SyncConnected == event.getState()) {
                connectionLatch.countDown();
            }
        }
    }

    static class IStringCallBack implements AsyncCallback.StringCallback {

        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.println("create path : " + rc + ", " + path + ", " + ctx + ", " + name);
        }
    }
}
