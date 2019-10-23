package own.jdk;

import java.util.Random;
import java.util.concurrent.*;

/**
 * CompletionService实现了生产者提交任务和消费者获取结果的解耦，生产者和消费者都不用关心任务的完成顺序，
 * 由CompletionService来保证，消费者一定是按照任务完成的先后顺序来获取执行结果。
 * <p>
 * ExecutorCompletionService是CompletionService的实现，融合了线程池Executor和阻塞队列BlockingQueue的功能。
 */
public class CompletionServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorCompletionService<Object> completionService = new ExecutorCompletionService<>(executorService);
        Random random = new Random(3);
        for (int i = 0; i < 10; i++)
            completionService.submit(() -> {
                int nextInt = random.nextInt(3);
                TimeUnit.SECONDS.sleep(nextInt);
                return "hello : " + nextInt + 10;
            });
        for (int i = 0; i < 10; i++)
            System.out.println(completionService.take().get());

        executorService.shutdown();
    }
}
