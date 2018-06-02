package own.stu.java.concurrence.action.chapter_7;

import java.util.concurrent.*;

/**
 * Created by CHANEL on 2018/5/27.
 */
public class CancelMission {
    private static final ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit){
        Future task = service.submit(r);
        try {
            task.get(timeout, unit);
        } catch (InterruptedException e) {
            System.out.println("interrupted .. ");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException .. ");
        } catch (TimeoutException e) {
            System.out.println("TimeoutException .. ");
        } finally {
            task.cancel(true);
        }
    }

    public static void main(String[] args) {
        CancelMission.timedRun(()->{
            System.out.println("start ... ");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("interrupted ..." + Thread.currentThread().getName());
            }
            System.out.println("end .... ");
        }, 3, TimeUnit.SECONDS);
    }
}
