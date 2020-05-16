package own.stu.code.demo;

public class NoSuitSpaceInSurvivor {

    /**
     * -XX:NewSize=10485760 -XX:MaxNewSize=10485760
     * -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520
     * -XX:SurvivorRatio=8
     * -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=10485760
     * -XX:+UseParNewGC - XX:+UseConcMarkSweepGC
     * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
     *
     * @param args
     */
    public static void main(String[] args) {

        byte[] arrays1 = new byte[2 * 1024 * 1024];
        arrays1 = new byte[2 * 1024 * 1024];
        arrays1 = new byte[2 * 1024 * 1024]; // will be into old gene

        byte[] arrays2 = new byte[128 * 1024];
        arrays2 = null;

        byte[] arrays3 = new byte[2 * 1024 * 1024];
    }
}
