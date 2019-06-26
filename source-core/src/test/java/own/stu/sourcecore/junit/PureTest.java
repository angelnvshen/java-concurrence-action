package own.stu.sourcecore.junit;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import net.minidev.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.assertj.core.util.Lists;
import org.mockito.internal.verification.Only;
import own.stu.sourcecore.java.lock.cas.Counter;
import sun.tools.jps.Jps;

public class PureTest {

  public static void main_2(String[] args) {
//    junit.textui.TestRunner.run(CalculationTest.class);

    Map map = new HashMap();
    map.put("12", "32232");
    map.put("we", "32232");
    map.put("223", Lists.newArrayList("2", "3"));
    map.put("44", Lists.newArrayList());
    System.out.println(JSONObject.toJSONString(map));

    System.out.println("310111195410231232".length());
  }

  public static void main3(String[] args) throws InterruptedException, IOException {
//    System.out.println(getPid());
    String[] arguments = new String[]{"-q"};
    Jps.main(arguments);
  }

  private static String getPid() throws IOException {
    Process p = Runtime.getRuntime().exec("/Library/Java/JavaVirtualMachines/jdk1.8.0_91.jdk/Contents/Home/bin/jps");
    InputStream in = p.getInputStream();
    List<String> jpsLines = IOUtils.readLines(in);
    IOUtils.closeQuietly(in);
    for (String line : jpsLines) {
      if (line.contains(PureTest.class.getSimpleName())) {
        return line.split("\\s")[0];
      }
    }
    throw new IllegalStateException("拿不到pid");
  }

  public static void main(String[] args) throws InterruptedException {
    Counter counter = new Counter();
    ExecutorService service = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 100; i++) {
      service.submit(() -> counter.increase());
    }
    service.shutdown();
    TimeUnit.SECONDS.sleep(1);
    System.out.println(counter.get());
  }

  public static void main2(String[] args) throws InterruptedException {

    Thread t = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        System.out.println("i = " + i);
        try {
          TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

//    t.setDaemon(true);
    t.start();
    printInfo();
    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
  }

  private static void printInfo() {
    String name = ManagementFactory.getRuntimeMXBean().getName();
    System.out.println(name);
    String pid = name.split("@")[0];
    System.out.println("Pid is:" + pid);


  }
}
