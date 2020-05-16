package own.stu.code.projectDemo;

public class BIDemo2 {
    /**
     * -XX:NewSize=104857600 -XX:MaxNewSize=104857600
     * -XX:InitialHeapSize=209715200 -XX:MaxHeapSize=209715200
     * -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     * -XX:PretenureSizeThreshold=20971520  // 大对象 20M
     * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
     * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
     *
     * ----- 优化 ------
     *
     * -XX:NewSize=209715200 -XX:MaxNewSize=209715200
     * -XX:InitialHeapSize=314572800 -XX:MaxHeapSize=314572800
     * -XX:SurvivorRatio=2 -XX:MaxTenuringThreshold=15
     * -XX:PretenureSizeThreshold=20971520
     * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
     * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30_000);
        while(true){
            loadData();
        }
    }

    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for(int i = 0; i < 4; i ++){
            data = new byte[10 * 1024 * 1024];
        }
        data = null;

        byte[] data1 = new byte[10 * 1024 * 1024];
        byte[] data2 = new byte[10 * 1024 * 1024];

        byte[] data3 = new byte[10 * 1024 * 1024];
        data3 = new byte[10 * 1024 * 1024];

        Thread.sleep(1000);
    }
}
