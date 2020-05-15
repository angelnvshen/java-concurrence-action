package own.jdk.thread;

import java.util.concurrent.*;

public class FutureTaskTest {

    public static void main1(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        get();
//        getWithTimeOut();
//        getWithTimeOutInterrupted(); // future caller thread can be interrupted, not influence the executors

        futureTryBeInterrupted();
    }

    private static void futureTryBeInterrupted() throws InterruptedException, ExecutionException, TimeoutException {
        Future<Integer> submit = getIntegerFuture();

        TimeUnit.MILLISECONDS.sleep(500);
        submit.cancel(true);
        System.out.println(" +++++++++++++++++ ");

        Integer result = submit.get();
//        Integer result = submit.get(2, TimeUnit.SECONDS);
        System.out.println(result);
    }


    private static void getWithTimeOutInterrupted() throws InterruptedException, ExecutionException, TimeoutException {
        Future<Integer> submit = getIntegerFuture();

        Thread currentThread = Thread.currentThread();
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentThread.interrupt();
        }).start();

        Integer result = submit.get(2, TimeUnit.SECONDS);
        System.out.println(result);
    }

    private static void getWithTimeOut() throws InterruptedException, ExecutionException, TimeoutException {
        Future<Integer> submit = getIntegerFuture();

        Integer result = submit.get(2, TimeUnit.SECONDS);
        System.out.println(result);
    }

    private static void get() throws InterruptedException, ExecutionException {
        Future<Integer> submit = getIntegerFuture();

        Integer result = submit.get();
        System.out.println(result);
    }

    private static Future<Integer> getIntegerFuture() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        return executorService.submit(() -> {
            System.out.println("dealing ....");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
            System.out.println("done .....");

            return 10;
        });
    }

    public static void main3(String[] args) throws Exception {
        FutureTask futureTask = new FutureTask(() -> {
            System.out.println(" run the task ");
            TimeUnit.HOURS.sleep(5);
            return Thread.currentThread().getName();
        });

        Thread thread = new Thread(futureTask);
        thread.start();

        new Thread(() -> {
            try {
                System.out.println("in " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
                System.out.println("after sleep  " + Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + " -> " + futureTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }, "thread - 1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(120);
                System.out.println("after sleep  " + Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + " -> cancel " + futureTask.cancel(true));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread - 2").start();

        TimeUnit.SECONDS.sleep(1);
//        System.out.println(futureTask.cancel(true));
        System.out.println("after sleep  " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " -> " + futureTask.get());
    }

    public static void main(String[] args) {

        FutureTask futureTask = new FutureTask(() -> {
            System.out.println(" run the task ");
            TimeUnit.HOURS.sleep(5);
            return Thread.currentThread().getName();
        });

        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(futureTask.cancel(true));
//            System.out.println(futureTask.cancel(false));
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
