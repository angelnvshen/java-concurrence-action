package own.stu.zookeeper.zoo.basic.chapter05.$5_4_1;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Get_Children_Sample {
    public static void main(String[] args) throws InterruptedException {

        String path = "/zk-book";
        ZkClient zkClient = new ZkClient(ZK_Constants.url, 5000);

        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println(parentPath + "'s child changed , currentChildren " + currentChilds);
            }
        });

        zkClient.createPersistent(path);

        TimeUnit.SECONDS.sleep(1);

        System.out.println(zkClient.getChildren(path));

        TimeUnit.SECONDS.sleep(1);

        zkClient.createPersistent(path + "/c1");

        TimeUnit.SECONDS.sleep(1);

        zkClient.delete(path + "/c1");

        TimeUnit.SECONDS.sleep(1);

        zkClient.delete(path);

        TimeUnit.SECONDS.sleep(100);


    }
}
