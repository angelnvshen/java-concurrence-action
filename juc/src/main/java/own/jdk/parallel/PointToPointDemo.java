package own.jdk.parallel;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PointToPointDemo {

    public static void main(String[] args) throws InterruptedException {

        for (int numRun = 0; numRun < 3; numRun++) {
            System.out.printf("Run %d \n", numRun);
            Phaser p0 = new Phaser(1);
            Phaser p1 = new Phaser(1);
            Phaser p2 = new Phaser(1);

            Thread thread1 = new Thread(() -> {
                dowork(100);
                p0.arrive();
                p1.awaitAdvance(0);
                dowork(300);
            });

            Thread thread2 = new Thread(() -> {
                dowork(200);
                p1.arrive();
                p0.awaitAdvance(0);
                p2.awaitAdvance(1);
                dowork(200);
            });

            Thread thread3 = new Thread(() -> {
                dowork(300);
                p2.arrive();
                p0.awaitAdvance(0);
                dowork(100);
            });

            long start = System.currentTimeMillis();
            thread1.start();
            thread2.start();
            thread3.start();
            thread1.join();
            thread2.join();
            thread3.join();
            printResult("Async-Version", System.currentTimeMillis() - start);

            long start2 = System.currentTimeMillis();
            dowork(100);
            dowork(300);

            dowork(200);
            dowork(200);

            dowork(300);
            dowork(100);
            printResult("Seq-Version", System.currentTimeMillis() - start2);

        }
    }

    public static void printResult(String msg, long cost) {
        System.out.printf("     %s completed in %d milliseconds\n", msg, cost);
    }

    private static void dowork(int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //TODO three staged and
    //    A   B
    //   / \ / \
    //  B   c  d
}
