package own.stu.zookeeper.zoo.basic.chapter05.$5_3_7;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AuthSample_delete {
    final static String PATH = "/zk-book-auth-test";
    final static String PATH2 = "/zk-book-auth-test/c1";
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = new ZooKeeper(ZK_Constants.url, 5000, null);

        zk.addAuthInfo("digest", "foo:true".getBytes());

        zk.create(PATH, "".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
//        zk.create(PATH2, "".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);

        System.out.println(zk.exists(PATH, null));

        //KeeperErrorCode = NodeExists for /zk-book-auth-tes
//        ZooKeeper zk1 = new ZooKeeper(ZK_Constants.url, 5000, null);
//        zk1.delete(PATH2, -1);

        /*ZooKeeper zk2 = new ZooKeeper(ZK_Constants.url, 5000, null);
        zk2.addAuthInfo("digest", "foo:true".getBytes());
        zk2.delete(PATH2, -1);*/

        /*ZooKeeper zk3 = new ZooKeeper(ZK_Constants.url, 5000, null);
        zk3.delete(PATH, -1);*/

        TimeUnit.SECONDS.sleep(300);
    }
}
