package own.jdk.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CLH implements Lock {

    private static class Node {
        private volatile boolean locked;
    }

    private final ThreadLocal<Node> preNode = ThreadLocal.withInitial(() -> null);
    private final ThreadLocal<Node> node = ThreadLocal.withInitial(() -> new Node());
    private AtomicReference<Node> tail = new AtomicReference<>(new Node());

    /**
     * 1.初始状态 tail指向一个node(head)节点
     * +------+
     * | head | <---- tail
     * +------+
     * <p>
     * 2.lock-thread加入等待队列: tail指向新的Node，同时Prev指向tail之前指向的节点
     * +----------+
     * | Thread-A |
     * | := Node  | <---- tail
     * | := Prev  | -----> +------+
     * +----------+        | head |
     * +------+
     * <p>
     * +----------+            +----------+
     * | Thread-B |            | Thread-A |
     * tail ---->  | := Node  |     -->    | := Node  |
     * | := Prev  | ----|      | := Prev  | ----->  +------+
     * +----------+            +----------+         | head |
     * +------+
     * 3.寻找当前node的prev-node然后开始自旋
     */
    @Override
    public void lock() {
        Node node = this.node.get();
        node.locked = true;
        Node preNode = tail.getAndSet(node);
        this.preNode.set(preNode);
        while (preNode.locked);
        /*while (preNode.locked) {
            try {
                TimeUnit.NANOSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tryLock() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    //获取当前线程的node，设置lock status，将当前node指向前驱node(这样操作tail指向的就是前驱node等同于出队操作).
    @Override
    public void unlock() {
        Node node = this.node.get();
        node.locked = false;
        this.node.set(this.preNode.get());
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) throws InterruptedException {
//        CLH lock = new CLH();
        ReentrantLock lock = new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        AtomicInteger num = new AtomicInteger();
        for (int n = 0; n < 10; n++)
            executorService.execute(() -> {
                for (int i = 0; i < 1000; i++) {
                    lock.lock();
                    num.addAndGet(i);
                    lock.unlock();
                }
            });

        executorService.shutdown();
        executorService.awaitTermination(4, TimeUnit.SECONDS);
        System.out.println(num.get());
    }
}
