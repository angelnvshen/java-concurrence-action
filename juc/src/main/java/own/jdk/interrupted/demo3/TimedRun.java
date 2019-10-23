package own.jdk.interrupted.demo3;

import java.util.concurrent.*;

/**
 * 通过Future 来取消任务
 */
public class TimedRun {

    private static final ExecutorService taskExec = Executors.newCachedThreadPool();

    public static void timedRun(Runnable r,
                                long timeout, TimeUnit unit)
            throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            // task will be cancelled below
        } catch (ExecutionException e) {
            // exception thrown in task; rethrow
            throw launderThrowable(e.getCause());
        } finally {
            // Harmless if task already completed
            task.cancel(true); // interrupt if running
        }
    }

    public static RuntimeException launderThrowable(Throwable t) {

        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }

    public static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {

        Task task = new Task();
        System.out.printf("Main: 开始\n");

        Future<String> future = executor.submit(task);

        try {
            future.get(300, TimeUnit.MILLISECONDS);//设置超时执行时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            //如果在任务中抛出了异常，那么重新抛出该异常
            throw launderThrowable(e.getCause());
        } catch (TimeoutException e) {
            e.printStackTrace();
            //接下来任务将被取消
        } finally {
            System.out.printf("执行取消任务 \n");
            future.cancel(true);//如果任务正在运行，那么将被中断
        }

        //将isCancelled()方法和isDone()的调用结果写入控制台，验证任务已取消，因此，已完成。
        System.out.printf("Canceled: " + future.isCancelled() + "\n");
        System.out.printf("Done: " + future.isDone() + "\n");
        //
        executor.shutdown();
        System.out.printf("The executor has finished\n");

    }
}