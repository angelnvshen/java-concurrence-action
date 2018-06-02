package own.stu.java.concurrence.action;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by CHANEL on 2018/5/27.
 */
public class ScheduledExecutorServiceTest {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.schedule(()->{
            System.out.println("start .... " + new Date());
        }, 3, TimeUnit.SECONDS);
        System.out.println("main .... " + new Date());
        service.shutdown();
    }
}
