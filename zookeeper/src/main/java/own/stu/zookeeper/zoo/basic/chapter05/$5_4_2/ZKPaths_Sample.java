package own.stu.zookeeper.zoo.basic.chapter05.$5_4_2;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.ZooKeeper;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

public class ZKPaths_Sample {

    static String path = "/curator_zkpath";
    static CuratorFramework client = CuratorFrameworkFactory.builder().
            sessionTimeoutMs(5000).
            connectString(ZK_Constants.url).
            connectionTimeoutMs(3000).
            retryPolicy(new ExponentialBackoffRetry(1000, 3)).
            build();

    public static void main(String[] args) throws Exception {
        client.start();
        ZooKeeper zooKeeper = client.getZookeeperClient().getZooKeeper();

        System.out.println(ZKPaths.fixForNamespace( path, "/sub"));
        System.out.println(ZKPaths.makePath(path, "sub"));
        System.out.println(ZKPaths.getNodeFromPath(path + "/sub1"));

        ZKPaths.PathAndNode pn = ZKPaths.getPathAndNode(path + "/sub1");
        System.out.println(pn.getNode());
        System.out.println(pn.getPath());

        String dir1 = path + "/child1";
        String dir2 = path + "/child2";

        ZKPaths.mkdirs(zooKeeper, dir1);
        ZKPaths.mkdirs(zooKeeper, dir2);

        System.out.println(ZKPaths.getSortedChildren(zooKeeper, path));

//        ZKPaths.deleteChildren(zooKeeper, path, true);
    }
}
