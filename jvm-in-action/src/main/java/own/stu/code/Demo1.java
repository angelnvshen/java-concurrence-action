package own.stu.code;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Demo1 {

    /*
     -XX:NewSize=209715200 -XX:MaxNewSize=209715200 -XX:InitialHeapSize=314572800 -XX:MaxHeapSize=314572800
        -XX:SurvivorRatio=2 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=20971520 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
        -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.txt
     */
    public static void main(String[] args) throws InterruptedException {
        List<Data> datas = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            datas.add(new Data());
        }

        TimeUnit.HOURS.sleep(1);
    }

    private static class Data {
    }


    /*
    =======================before
        -XX:NewSize=104857600 -XX:MaxNewSize=104857600 -XX:InitialHeapSize=209715200 -XX:MaxHeapSize=209715200
        -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=20971520 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
        -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.txt
    =======================after
        -XX:NewSize=209715200 -XX:MaxNewSize=209715200 -XX:InitialHeapSize=314572800 -XX:MaxHeapSize=314572800
        -XX:SurvivorRatio=2 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=20971520 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
        -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.txt
     */
    /*public static void main(String[] args) throws InterruptedException {

        Thread.sleep(20000);
        while (true){
            loadData();
        }
    }

    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for(int i = 0;i<4;i++){
            data = new byte[10 * 1024 * 1024];
        }
        data = null;

        byte[] data1 = new  byte[10 * 1024 * 1024];
        byte[] data2 = new  byte[10 * 1024 * 1024];

        byte[] data3 = new  byte[10 * 1024 * 1024];
        data3 = new  byte[10 * 1024 * 1024];

        Thread.sleep(1000);
    }*/

    /*
        -XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520
        -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
        -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.txt
     */
    /*public static void main(String[] args) throws InterruptedException {

        while (true){
            byte[] arrays1 = new byte[1 * 1024 * 1024];
            arrays1 = null;
//            byte[] arrays2 = new byte[2 * 1024 * 1024];
            TimeUnit.SECONDS.sleep(1);
        }
    }*/

    /*
        -XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520
        -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
        -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.txt
     */
    /*public static void main(String[] args) {

        byte[] arrays1 = new byte[7 * 1024 * 1024];
        arrays1 = null;
        byte[] arrays2 = new byte[3 * 1024 * 1024];*/

        /*byte[] arrays1 = new byte[4 * 1024 * 1024];
        arrays1 = null;

        byte[] arrays2 = new byte[2 * 1024 * 1024];
        byte[] arrays3 = new byte[2 * 1024 * 1024];
        byte[] arrays4 = new byte[2 * 1024 * 1024];
        byte[] arrays5 = new byte[128 * 1024];

        byte[] arrays6 = new byte[2 * 1024 * 1024];
    }*/

    /*
        -XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520
        -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=10485760 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
        -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
     */
    /*public static void main(String[] args) {

        byte[] arrays1 = new byte[9 * 1024 * 1024];  //大对象直接进入old区

        /*byte[] arrays1 = new byte[2 * 1024 * 1024];
        arrays1 = new byte[2 * 1024 * 1024];
        arrays1 = new byte[2 * 1024 * 1024];

        byte[] arrays2 = new byte[128 * 1024];
        arrays2 = null;

        byte[] arrays3 = new byte[2 * 1024 * 1024];*/

        /*byte[] arrays1 = new byte[2 * 1024 * 1024];
        arrays1 = new byte[2 * 1024 * 1024];
        arrays1 = new byte[2 * 1024 * 1024];
        arrays1 = null;

        byte[] arrays2 = new byte[128 * 1024];
        byte[] arrays3 = new byte[2 * 1024 * 1024];

        arrays3 = new byte[2 * 1024 * 1024];
        arrays3 = new byte[2 * 1024 * 1024];
        arrays3 = new byte[128 * 1024];
        arrays3 = null;

        byte[] arrays4 = new byte[2 * 1024 * 1024];
    }*/


    /*
        -XX:NewSize=5242880 -XX:MaxNewSize=5242880 -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760
        -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=10485760 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
        -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
     */
    /*public static void main(String[] args) {
        byte[] arrays1 = new byte[1024 * 1024];
        arrays1 = new byte[1024 * 1024];
        arrays1 = new byte[1024 * 1024];
        arrays1 = null;

        byte[] arrays2 = new byte[2 * 1024 * 1024];
    }*/
}






















