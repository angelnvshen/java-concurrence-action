package own.jvm;

/**
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:-DoEscapeAnalysis 关闭逃逸分析（1.6之后 ，默认开启）
 *
 *
 */
public class DoEscapeAnalysis {
    public static void main(String[] args) throws InterruptedException {
        while (true){
//            Integer integer = new Integer(123456789);
            Thread.sleep(5000);
            System.out.println(123);
        }
    }
}
