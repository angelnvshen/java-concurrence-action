package own.stu.code.projectDemo;

public class BIDemo {
    /**
     * -XX:NewSize=104857600 -XX:MaxNewSize=104857600
     * -XX:InitialHeapSize=209715200 -XX:MaxHeapSize=209715200
     * -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     * -XX:PretenureSizeThreshold=3145728  // 大对象 3M
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
        for(int i = 0; i < 50; i ++){
            data = new byte[100 * 1024];
        }
        data = null;

        Thread.sleep(1000); // 模拟bi每秒 产生50M数据
    }
}
