package own.stu.java.concurrence.action;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CopyOnWriteTest {

    CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
    ExecutorService service = Executors.newCachedThreadPool();

    @Test
    public void testRead() throws InterruptedException {

        list.addAll(Arrays.asList("10", "xxx", "222", "CCCC", "JJJJ"));

        for(int i=0;i<10;i++){
            service.submit(() -> {
                for (int k = 0; k < 100; k++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(list.get(new Random().nextInt(list.size())) + " - ");
                }
                System.out.println();
            });
        }

        TimeUnit.HOURS.sleep(1);
    }

    @Test
    public void testWrite(){
        list.addAll(Arrays.asList("---1111", "---2222", "---3333", "---44444", "---5555"));
    }

}
