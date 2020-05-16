package own.stu.code.demo;

public class DynamicAge {

    /**
     * -XX:NewSize=10485760 -XX:MaxNewSize=10485760
     * -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520
     * -XX:SurvivorRatio=8
     * -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=10485760
     * -XX:+UseParNewGC - XX:+UseConcMarkSweepGC
     * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
     * @param args
     */
    public static void main(String[] args) {

        // explain:
        // [ParNew: 7613K->548K(9216K), 0.0006468 secs]
        // 之前新生代占用大小 -> minor gc之后 新生代大小

        byte[] arrays1 = new byte[2 * 1024 * 1024];
        arrays1 = new byte[2 * 1024 * 1024];
        arrays1 = new byte[2 * 1024 * 1024];
        arrays1 = null;

        byte[] arrays2 = new byte[128 * 1024]; // 第二次minor gc会应为动态年龄进入老年代

        byte[] arrays3 = new byte[2 * 1024 * 1024];
        arrays3 = new byte[2 * 1024 * 1024];
        arrays3 = new byte[2 * 1024 * 1024];
        arrays3 = new byte[128 * 1024];
        arrays3 = null;

        byte[] arrays4 = new byte[2 * 1024 * 1024];
    }
}
