package own.stu.highConcurrence.cacheconsistence;

import com.google.protobuf.Internal;

import javax.annotation.processing.Completion;
import java.util.concurrent.*;

public class OwnTest {

    public static void main1(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(4, 4, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(10));
        Future<String> future = service.submit(() -> {
            long start = System.currentTimeMillis();
            System.out.println(" -------  " + start);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" -------  " + (System.currentTimeMillis() - start));
            return "XXX";
        });

        System.out.println(future.get());
    }

    private static void sleep(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printCode(Object o, String name) {
        System.out.println(String.format("%s (code) -> ", name) + o);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello world f1");
            sleep(1); // TimeUnit.SECONDS.sleep(1)
            return "result f1";
        });
        printCode(f1, "f1");

        CompletableFuture<String> f2 = f1.thenApply(r -> {
            System.out.println(r);
            sleep(1);
            return "f2";
        });
        printCode(f2, "f2");

        CompletableFuture<String> f3 = f2.thenApply(r -> {
            System.out.println(r);
            sleep(1);
            return "f3";
        });
        printCode(f3, "f3");

        CompletableFuture<String> f4 = f1.thenApply(r -> {
            System.out.println(r);
            sleep(1);
            return "f4";
        });
        printCode(f4, "f4");

        CompletableFuture<String> f5 = f4.thenApply(r -> {
            System.out.println(r);
            sleep(1);
            return "f5";
        });
        printCode(f5, "f5");

        CompletableFuture<String> f6 = f5.thenApply(r -> {
            System.out.println(r);
            sleep(1);
            return "f6";
        });
        printCode(f6, "f6");
        sleep(20);
    }

    public static void main2(String[] args) throws InterruptedException, ExecutionException {
        /*Object bo = new Object();
        Object bo1 = new Object();
        if(bo.getClass() instanceof Object){
            System.out.println(" ====== ");
        }
        System.out.println(bo.getClass() == bo1.getClass());*/

        /*SafeCalc calc = new SafeCalc();

        Runnable multi = () -> {
            for (int i = 0; i < 100; i++) {
                calc.addOne();
                System.out.println(Thread.currentThread().getName() + " --> " + calc.get());
                System.out.println(Thread.currentThread().getName() + " ==> " + calc.get2());
            }
        };

        Thread t1 = new Thread(multi, "t-1");
        Thread t2 = new Thread(multi, "t-2");
        Thread t3 = new Thread(multi, "t-3");

        t1.start();
        t2.start();
        t3.start();

        Thread.currentThread().join();*/

        /*ExecutorService service = Executors.newCachedThreadPool();
        Future<?> future = service.submit(() -> {
            System.out.println(" ---------- ");
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" +++++++++ ");
        });

        future.get();*/

//        System.out.println(0xf0000000);
//        System.out.println(Integer.MAX_VALUE);

        /*CompletableFuture
                .runAsync(() -> System.out.println(1))
                .thenRun(() -> System.out.println(2));

        System.out.println(3);*/

        // 创建一个带result的CompletableFuture
        /*CompletableFuture<String> future = CompletableFuture.completedFuture("result");
        System.out.println(future.get());*/

        // 默认创建的CompletableFuture是没有result的，这时调用future.get()会一直阻塞下去知道有result或者出现异常
        /*CompletableFuture<String> future = new CompletableFuture<>();
        try {
            System.out.println(" +++++ " + future.get(1, TimeUnit.SECONDS));
        } catch (Exception e) {
            // no care
            System.out.println(e);
        }

        // 给future填充一个result
        future.complete("result");
        assert "result".equals(future.get());

        // 给future填充一个异常
        future = new CompletableFuture<>();
        future.completeExceptionally(new RuntimeException("exception"));
        try {
            future.get();
        } catch (Exception e) {
            assert "exception".equals(e.getCause().getMessage());
        }*/


        /*CompletableFuture.runAsync(() -> {
            System.out.println("hello world");
        });*/

        /*CompletableFuture.supplyAsync(() -> {
            System.out.println("hello world");
            return "result";
        });*/

        /*CompletableFuture.supplyAsync(() -> {
            System.out.println(" ----  ");
            return "hello";
        }).whenCompleteAsync((result, e) -> {
            System.out.println(result + " " + e);
        }).exceptionally((e) -> {
            System.out.println("exception " + e);
            return "exception";
        });*/
    }

    private static Runnable multi(Runnable a) {
        return () -> {
            for (int i = 0; i < 100; i++) {
                a.run();
            }
        };
    }


    static class SafeCalc {
        long value = 0L;

        synchronized long get() {
            return value;
        }

        long get2() {
            return value;
        }

        synchronized void addOne() {
            value += 1;
        }
    }
}
