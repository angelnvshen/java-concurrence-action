package own.leetcode.muilteThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class H2O {

    private Semaphore hy = null;
    private Semaphore ox = null;

    CyclicBarrier barrier = new CyclicBarrier(3, () -> {
        hy.release(2);
        ox.release(1);
    });

    public H2O() {
        hy = new Semaphore(2);
        ox = new Semaphore(1);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        hy.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();

        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        ox.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
       /* H2O h2O = new H2O();
        int num = 3;

        Thread h = new Thread(() -> {
            h2O.
        });
        h.getName();
        for (int i = 0; i < num; i++) {
            h2O.hydrogen(() -> System.out.print("H"));
            h2O.hydrogen(() -> System.out.print("H"));
            h2O.oxygen(() -> System.out.print("O"));
        }*/
    }
}