package own.leetcode.muilteThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class FooBar {

    public static void main(String[] args) throws InterruptedException {
        FooBar bar = new FooBar(3);
        Thread bar1 = new Thread(() -> {
            try {
                bar.bar(() -> System.out.println("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        bar1.start();

        Thread foo = new Thread(() -> {
            try {
                bar.foo(() -> System.out.println("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        foo.start();

        bar1.join();
        foo.join();
        System.out.println(" ------- ");
    }

    private int n;

    private Semaphore fs = new Semaphore(1);
    private Semaphore bs = new Semaphore(0);
    private CyclicBarrier barrier = new CyclicBarrier(2, () -> {
        fs.release();
//        try {
//            bs.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    });


    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {

            fs.acquire();
        	// printFoo.run() outputs "foo". Do not change or remove this line.
        	printFoo.run();

        	bs.release();
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {

            bs.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
        	printBar.run();

            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}