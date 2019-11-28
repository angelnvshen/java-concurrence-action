package own.jdk.executorService;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * period 2 second start a task, and one task cost 5 s
 *
 * quartz | crontab | controll-M
 * 执行逻辑是 0 2 4 ... 的间隔执行，不会关心任务是否完成。
 *
 * scheduleExecutor | timer
 * 执行逻辑是 等待任务执行后才会执行下个任务
 *
 *
 *
 *
 * scheduleAtFixedRate ，是以上一个任务开始的时间计时，120秒过去后，
 * 检测上一个任务是否执行完毕，如果上一个任务执行完毕，则当前任务立即执行，
 * 如果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行。
 * <p>
 * scheduleWithFixedDelay，是以上一个任务结束时开始计时，120秒过去后，立即执行。
 * <p>
 * 注意：以上两个方法执行时，如果任务发生异常，需要进行处理，否则后续任务将不执行
 */
public class ScheduledExecutorServiceTest {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10);

        executorService.schedule(() -> System.out.println("heelo owld : " + System.currentTimeMillis()),
                2, TimeUnit.SECONDS);

        // 每隔2s执行一次，但是任务执行了3s，所以要等到任务之后完成之后，在执行下一个任务。故执行结果的间隔 为 3S
        executorService.scheduleAtFixedRate(() -> {
                    System.out.println("at fixed rate : " + System.currentTimeMillis());
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                2, 2, TimeUnit.SECONDS);

        // 执行结果的间隔 为 5S
        executorService.scheduleWithFixedDelay(() -> {
                    System.out.println("with fixed delay : " + System.currentTimeMillis());
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                2, 2, TimeUnit.SECONDS);
    }
}
