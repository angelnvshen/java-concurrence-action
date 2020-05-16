package own.stu.code.demo;

public class AgeOf15 {

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

        byte[] arrays2 = new byte[1 * 1024]; // 对象达到15岁年龄之后自然进入老年代

        byte[] arrays3 = null;
        for (int i = 0; i < 15; i++) {
            arrays3 = null;
            arrays3 = new byte[2 * 1024 * 1024];
            arrays3 = null;
            arrays3 = new byte[2 * 1024 * 1024];
            arrays3 = null;
            arrays3 = new byte[2 * 1024 * 1024];
            arrays3 = null;
            arrays3 = new byte[2 * 1024 * 1024];
            arrays3 = null;
        }
    }
}
