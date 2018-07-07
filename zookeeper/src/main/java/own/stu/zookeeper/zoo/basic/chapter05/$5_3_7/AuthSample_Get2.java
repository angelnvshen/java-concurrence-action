package own.stu.zookeeper.zoo.basic.chapter05.$5_3_7;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AuthSample_Get2 {
    final static String PATH = "/zk-book-auth-test";
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = new ZooKeeper(ZK_Constants.url, 5000, null);
        zk.addAuthInfo("digest", "foo:true".getBytes());
        zk.create(PATH, "Hello".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        System.out.println(zk.exists(PATH, null));

        ZooKeeper zk2 = new ZooKeeper(ZK_Constants.url, 5000, null);
        zk2.addAuthInfo("digest", "foo:true".getBytes());
        System.out.println(new String(zk2.getData(PATH, false,null)));

        ZooKeeper zk3 = new ZooKeeper(ZK_Constants.url, 5000, null);
        zk3.addAuthInfo("digest", "foo:false".getBytes());
        System.out.println(new String(zk3.getData(PATH, false,null)));

        TimeUnit.SECONDS.sleep(300);
    }
}
