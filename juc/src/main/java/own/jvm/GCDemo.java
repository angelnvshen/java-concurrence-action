package own.jvm;

import java.util.Random;

public class GCDemo {

  /**
   *
   *  -Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC (DefNew + Tenured)
   *
   *  -Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC (ParNew + Tenured)
   *
   *  1.8默认的GC parallelGC
   *  -Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC (PSYoung + PSOld)
   *
   *  -Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC (PSYoungGen  + ParOldGen)
   *
   *  -Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC (ParNew  + concurrent mark-sweep generation)
   *
   *
   * @param args
   */
  public static void main(String[] args) {
    String xx = "helloGC";

    while (true) {
      xx += xx + new Random(10).nextInt(1000000) + new Random(10).nextInt(99999);
      xx.intern();
    }
  }
}
