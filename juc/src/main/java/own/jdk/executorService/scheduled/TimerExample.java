package own.jdk.executorService.scheduled;

import java.util.concurrent.TimeUnit;

/**
 * @see own.jdk.schedual.TimerTest
 *
 * one thread execute the task , and the task store in the priority queue.
 */
public class TimerExample {

    public static void printNow(){
        System.out.println(now());
    }

    public static long now() {
        return System.currentTimeMillis();
    }

    public static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
