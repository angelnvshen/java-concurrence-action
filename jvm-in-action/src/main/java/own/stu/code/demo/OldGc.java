package own.stu.code.demo;

public class OldGc {

    /**
     * -XX:NewSize=10485760 -XX:MaxNewSize=10485760
     * -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520
     * -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     * -XX:PretenureSizeThreshold=3145728  // 大对象 3M
     * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
     * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
     *
     * @param args
     */
    public static void main(String[] args) {

        byte[] arrays1 = new byte[4 * 1024 * 1024];
        arrays1 = null;

        byte[] arrays2 = new byte[2 * 1024 * 1024];
        byte[] arrays3 = new byte[2 * 1024 * 1024];
        byte[] arrays4 = new byte[2 * 1024 * 1024];
        byte[] arrays5 = new byte[128 * 1024];

        byte[] arrays6 = new byte[2 * 1024 * 1024];
    }
}
