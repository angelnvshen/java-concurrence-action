package own.jdk.executorService.scheduled;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @see own.jdk.schedual.TimerTest
 * <p>
 * one thread execute the task , and the task store in the priority queue.
 */
public class TimerExample {

    public static void printNow() {
        System.out.println(now());
    }

    public static long now() {
        return System.currentTimeMillis();
    }

    public static void sleep(int seconds) {
        sleep(seconds, SECONDS);
    }

    public static void sleep(int timeout, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
