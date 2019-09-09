package own.stu.jdk7;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    public static void main(String[] args) throws InterruptedException {
        TestO t0 = new TestO();
        TestO t1 = new TestO();
        TestO t2 = new TestO();
        TestO t3 = new TestO();
        TestO t4 = new TestO();
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t0.join();
        t1.join();
        t2.join();
        t4.join();
        t3.join();
        System.out.println(TestO.at.get());
    }


    static class TestO extends Thread {
        static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(2);
        static AtomicInteger at = new AtomicInteger();

        public void run() {
            while (at.get() < 100000) {
                map.put(at.get(), at.get());
                at.incrementAndGet();
            }
        }
    }
}