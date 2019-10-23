package own.jdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest2 {

    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    AtomicInteger num = new AtomicInteger(10);

    //read -> write
    public static void main3(String[] args) {
        ReentrantReadWriteLockTest2 test2 = new ReentrantReadWriteLockTest2();
        ReentrantReadWriteLock readWriteLock = test2.readWriteLock;

        readWriteLock.readLock().lock();
        System.out.println("get read lock");

        readWriteLock.writeLock().lock();
        System.out.println("write Lock");
    }

    // write -> read
    public static void main2(String[] args) {
        ReentrantReadWriteLockTest2 test2 = new ReentrantReadWriteLockTest2();
        ReentrantReadWriteLock readWriteLock = test2.readWriteLock;
        readWriteLock.writeLock().lock();
        System.out.println("writeLock");
        readWriteLock.readLock().lock();
        System.out.println("get read lock");
    }

    public static void main1(String[] args) throws InterruptedException {
        ReentrantReadWriteLockTest2 test2 = new ReentrantReadWriteLockTest2();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(test2.geneRead(5, "read-1"));
        executorService.execute(test2.geneRead(5, "read-2"));
        executorService.execute(test2.geneWrite(3, "write-1"));
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(test2.geneRead(5, "read-3"));
        executorService.shutdown();
    }


    private Runnable geneRead(int sleepTime, String name) {
        return () -> {
            try {
                Thread.currentThread().setName(name);
                readLock.lock();
                System.out.println(Thread.currentThread().getName() + " : " + num.get());
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        };
    }

    private Runnable geneWrite(int sleepTime, String name) {
        return () -> {
            try {
                Thread.currentThread().setName(name);
                writeLock.lock();
                System.out.println(Thread.currentThread().getName() + " : " + num.addAndGet(10));
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        };
    }

    private RunnableWithName geneReadRunnableWithName(int sleepTime, String name) {
        return new RunnableWithName() {

            @Override
            public String getName() {
                return name;
            }

            @Override
            public void run() {
                try {
                    Thread.currentThread().setName(name);
                    readLock.lock();
                    System.out.println(Thread.currentThread().getName() + " : " + num.get());
                    TimeUnit.SECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readLock.unlock();
                }
            }
        };
    }

    interface RunnableWithName extends Runnable {
        String getName();
    }
}
