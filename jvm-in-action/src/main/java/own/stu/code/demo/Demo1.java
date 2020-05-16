package own.stu.code.demo;

public class Demo1 {

    /**
     * -XX:NewSize=5242880 -XX:MaxNewSize=5242880
     * -XX:InitialHeapSize=10485760
     * -XX:MaxHeapSize=10485760 -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=10485760 // 大对象
     * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
     * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
     * @param args
     */
    public static void main(String[] args) {
        byte[] arrays1 = new byte[1024 * 1024];
        arrays1 = new byte[1024 * 1024];
        arrays1 = new byte[1024 * 1024];
        arrays1 = null;
        byte[] arrays2 = new byte[2 * 1024 * 1024];
    }
}
