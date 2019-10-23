package own.jdk.interrupted.demo2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 计时任务的取消
 */

// 在外部线程中安排中断（不要这样做）
public class TimedRun1 {

    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(1);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) {
        final Thread taskThread = Thread.currentThread();

        cancelExec.schedule(() -> {
            taskThread.interrupt();
            System.out.println("1--" + taskThread.isInterrupted());
        }, timeout, unit);
        r.run();
        System.out.println("2--" + taskThread.isInterrupted());
    }

    /**
     * 任务不响应中断，那么 timedRun 会在任务结束时才返回，此时可能已经超过了指定的时限（或者还没有超过时限）。
     * 如果某个限时运行的服务没有在指定的时间内返回，那么将对调用者带来负面影响。
     *
     * @param args
     */
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        Runnable runnable = () -> {
            int i = 0;
            for (int j = 0; j < 100_000_000; j++) {
                i++;
                if (i % 10_000_000 == 0) {
                    System.out.println(i + "  " + Thread.currentThread().getName());
                }
            }
        };

        TimedRun1.timedRun(runnable, 1, TimeUnit.MILLISECONDS);
        System.out.println(System.currentTimeMillis() - start);
        cancelExec.shutdown();
    }
}
