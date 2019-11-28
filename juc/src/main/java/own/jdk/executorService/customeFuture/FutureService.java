package own.jdk.executorService.customeFuture;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * Future        -> 代表未来结果的一个票据
 * Callable      -> 代表一个执行的任务
 * FutureService -> 桥接，隔离任务和票据
 * <p>
 * ===== MARKING [future]======
 */
public class FutureService {

    public static <T> Future<T> submit(Callable<T> job, Consumer<T> consumer) {
        AsyncFuture<Object> objectAsyncFuture = new AsyncFuture<>();
        new Thread(() -> {
            try {
                T call = job.call();
                objectAsyncFuture.done(call);
                if (consumer != null)
                    consumer.accept(call);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return objectAsyncFuture;
    }

    public static <T> Future<T> submit(Callable<T> job) {
        return submit(job, null);
    }
}
