package own.jdk.multiThreadPattern.chapter5_twoPhaseTermination;

import java.lang.ref.WeakReference;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程停止标志。
 */
public class TerminationToken {

    // 使用volatile修饰，以保证无需显式锁的情况下该变量的内存可见性
    protected volatile boolean toShutdown = false;
    // 用于目标线程还有多少数量未完成的任务，以支持等待目标线程处理完其任务后再停止
    public final AtomicInteger reservations = new AtomicInteger(0);

    /*
     * 在多个可停止线程实例共享一个TerminationToken实例的情况下，该队列用于
     * 记录那些共享TerminationToken实例的可停止线程，以便尽可能减少锁的使用 的情况下，
     * 实现这些线程的停止。
     */
    private final Queue<WeakReference<Terminable>> coordinatedThreads;

    public TerminationToken() {
        this.coordinatedThreads = new ConcurrentLinkedQueue<>();
    }

    public boolean isToShutdown() {
        return toShutdown;
    }

    protected void setToShutdown(boolean toShutdown) {
        this.toShutdown = true;
    }

    protected void register(Terminable thread) {
        coordinatedThreads.add(new WeakReference<>(thread));
    }

    /**
     * 通知TerminationToken实例：共享该实例的所有可停止线程中的一个线程停止了， 以便其停止其它未被停止的线程。
     *
     * @param thread 已停止的线程
     */
    protected void notifyThreadTermination(Terminable thread) {
        WeakReference<Terminable> wrThread;
        Terminable otherThread;
        while ((wrThread = coordinatedThreads.poll()) != null) {
            otherThread = wrThread.get();
            if (otherThread != null && otherThread != thread) {
                otherThread.terminate();
            }
        }
    }
}
