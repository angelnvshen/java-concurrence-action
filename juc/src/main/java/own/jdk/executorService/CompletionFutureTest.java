package own.jdk.executorService;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletionFutureTest {
    @Test
    public void test() throws InterruptedException {
        CompletableFuture.
                runAsync(() -> {
                    System.out.println("hello ...... ");
                    sleep(500);
                    System.out.println("world ...... ");

                })
                .thenAccept((t) -> System.out.println("then ....." + t))
                .whenCompleteAsync((v, t) -> System.out.println("complete ...." + t));
        Thread.currentThread().join();
    }

    public static void sleep(int millSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(millSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
