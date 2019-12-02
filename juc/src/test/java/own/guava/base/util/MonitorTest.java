package own.guava.base.util;

import com.google.common.util.concurrent.Monitor;
import org.junit.Test;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import static own.jdk.executorService.scheduled.TimerExample.sleep;

public class MonitorTest {

    AtomicInteger num = new AtomicInteger(0);

    @Test
    public void test_syncLock() {
        //SyncQueue queue = new SyncQueue();
        //LockQueue queue = new LockQueue();
        MonitorQueue queue = new MonitorQueue();

        IntStream.range(0, 3).boxed().forEach((i) -> {
            new Thread(() -> {
                for (; ; ) {
                    int andIncrement = num.getAndIncrement();
                    queue.offer(andIncrement);
                    System.out.println(Thread.currentThread().getName() + " offer -> " + andIncrement);
                    sleep(200, TimeUnit.MILLISECONDS);
                }
            }, "Thread-" + i).start();
        });
        IntStream.range(4, 6).boxed().forEach((i) -> {
            new Thread(() -> {
                for (; ; ) {
                    System.out.println(Thread.currentThread().getName() + " take -> " + queue.take());
                    sleep(200, TimeUnit.MILLISECONDS);
                }
            }, "Thread-" + i).start();
        });

        sleep(600_000);
    }

    class MonitorQueue<T> {
        LinkedList<T> queue = new LinkedList<>();
        int max = 10;
        Monitor monitor = new Monitor();

        public void offer(T t) {
            try {
                monitor.enterWhen(monitor.newGuard(() -> queue.size() < max));
                queue.addLast(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                monitor.leave();
            }
        }

        public synchronized T take() {
            try {
                monitor.enterWhen(monitor.newGuard(() -> queue.size() != 0));
                T t = queue.removeFirst();
                return t;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                monitor.leave();
            }
        }
    }

    class SyncQueue<T> {

        LinkedList<T> queue = new LinkedList<>();
        int max = 10;

        public synchronized void offer(T t) {
            while (queue.size() >= max) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(t);
            notifyAll();
        }

        public synchronized T take() {
            while (queue.size() == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            notifyAll();
            return t;
        }
    }

    class LockQueue<T> {

        ReentrantLock lock = new ReentrantLock();
        Condition full = lock.newCondition();
        Condition empty = lock.newCondition();
        LinkedList<T> queue = new LinkedList<>();
        int max = 10;

        public void offer(T t) {
            try {
                lock.lock();
                while (queue.size() >= max) {
                    full.await();
                }

                queue.addLast(t);
                empty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public synchronized T take() {
            try {
                lock.lock();
                while (queue.size() == 0) {
                    empty.await();
                }
                T t = queue.removeFirst();
                full.signalAll();
                return t;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

}
