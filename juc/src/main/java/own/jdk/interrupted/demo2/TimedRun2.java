package own.jdk.interrupted.demo2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 7.9 在专门的线程中中断任务
 */
public class TimedRun2 {
    private static final ScheduledExecutorService cancelExec = Executors
            .newScheduledThreadPool(1);

    public static void timedRun(final Runnable r, long timeout, TimeUnit unit)
            throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() {
                if (t != null)
                    try {
                        throw launderThrowable(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(new Runnable() {
            public void run() {
                taskThread.interrupt();
                System.out.println("1--" + taskThread.isInterrupted());
            }
        }, timeout, unit);
        taskThread.join(unit.toMillis(timeout));
        task.rethrow();
        System.out.println("2--" + taskThread.isInterrupted());
    }

    public static Exception launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }

    public static void main(String[] args) {
        TimedRun2 timeRun = new TimedRun2();
        Runnable run = () -> {
            int i = 0;
            for (int j = 0; j < 100000000; j++) {
                i++;
                if (i % 10000000 == 0) {
                    System.out.println(i + "  " + Thread.currentThread().getName());
                }
            }
        };
        try {
            timeRun.timedRun(run, 1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}