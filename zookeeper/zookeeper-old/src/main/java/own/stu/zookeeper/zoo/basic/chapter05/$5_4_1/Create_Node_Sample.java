package own.stu.zookeeper.zoo.basic.chapter05.$5_4_1;

import org.I0Itec.zkclient.ZkClient;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

public class Create_Node_Sample {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(ZK_Constants.url, 5000);
//        zkClient.createPersistent("/zk-book/c1", true);
        zkClient.deleteRecursive("/zk-book");
    }
}
