package own.jdk.executorService.customeFuture;

import java.util.concurrent.TimeUnit;

public class FutureTest {

    public static void main(String[] args) throws InterruptedException {

//        get();

        Future<String> submit = FutureService.submit(() -> {
            TimeUnit.SECONDS.sleep(4);
            return "HELLO";
        }, (t) -> System.out.println(t));

        System.out.println(" do other thing ...");
//        System.out.println(submit.get());
        TimeUnit.SECONDS.sleep(5);
    }

    public static String get() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        return "HELLO";
    }
}
