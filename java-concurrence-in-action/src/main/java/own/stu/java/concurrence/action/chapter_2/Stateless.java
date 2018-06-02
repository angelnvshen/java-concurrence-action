package own.stu.java.concurrence.action.chapter_2;

import net.jcip.annotations.ThreadSafe;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * stateless
 */
@ThreadSafe
public class Stateless {

    private void test(int i) {
        System.out.println(factor(i));
    }

    Integer[] factor(Integer i) {
        // Doesn't really factor
        return new Integer[]{i};
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Stateless stateless = new Stateless();
        for (int i = 0; i < 10; i++)
            service.submit(() -> {
                stateless.test(new Random().nextInt(100));
            });
        service.shutdown();
    }
}


