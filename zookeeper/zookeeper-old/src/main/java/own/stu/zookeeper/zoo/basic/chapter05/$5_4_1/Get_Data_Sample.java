package own.stu.zookeeper.zoo.basic.chapter05.$5_4_1;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import own.stu.zookeeper.zoo.basic.constant.ZK_Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Get_Data_Sample {
    public static void main(final String[] args) throws InterruptedException {

        String path = "/zk-book";
        ZkClient zkClient = new ZkClient(ZK_Constants.url, 5000);
        zkClient.createPersistent(path, "hello, beau ");
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println(dataPath + "'s data changed, new data : " + data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(dataPath + "'s data deleted ");
            }
        });

        System.out.println(zkClient.readData(path));

        zkClient.writeData(path , "hello , lok");
        TimeUnit.SECONDS.sleep(1);
        zkClient.delete(path);

        TimeUnit.SECONDS.sleep(100);


    }
}
