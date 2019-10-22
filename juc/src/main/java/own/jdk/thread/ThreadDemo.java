package own.jdk.thread;

import java.sql.Time;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo {

    public static void main6(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1800);
            } catch (InterruptedException e) {
                System.out.println("---- interrupted -----");
            }
            System.out.println(" quit ");
        });
        thread.start();

        thread.interrupt();
        System.out.println(" main out ");
    }

    private static final class PrimerTask implements Callable<Boolean> {

        private final long num;

        public PrimerTask(long num) {
            this.num = num;
        }

        @Override
        public Boolean call() {

            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(" --- interrupted ----");
                Thread.currentThread().interrupt();
            }

            // i < num 让任务有足够的运行时间
            for (long i = 2; i < num; i++) {
                if (Thread.currentThread().isInterrupted()) { // 任务被取消
                    System.out.println("PrimerTask.call： 你取消我干啥？");
                    return false;
                }
                if (num % i == 0) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main5(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        long num = 1000000033L;
        PrimerTask task = new PrimerTask(num);
        Future<Boolean> future = threadPool.submit(task);
        threadPool.shutdown(); // 发送关闭线程池的指令

        cancelTask(future, 2_000); // 在 2 秒之后取消该任务

        try {
            boolean result = future.get();
            System.out.format("%d 是否为素数？ %b\n", num, result);
        } catch (CancellationException ex) {
            System.err.println("任务被取消");
        } catch (InterruptedException ex) {
            System.err.println("当前线程被中断");
        } catch (ExecutionException ex) {
            System.err.println("任务执行出错");
        }
    }

    private static void cancelTask(final Future<?> future, final int delay) {

        Runnable cancellation = () -> {
            try {
                Thread.sleep(delay);
                future.cancel(true); // 取消与 future 关联的正在运行的任务
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.err);
            }
        };

        new Thread(cancellation,"cancel-thread").start();
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<String> callable = () -> {
            try {
                TimeUnit.HOURS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("interrupted ... ");
                //throw new RuntimeException(e.getCause());
            }
            int i = 100 / 0;
            return "xx";
        };

//        Future<String> submit = executorService.submit(callable);
//
//        System.out.println(" ---------- ");
//        System.out.println(submit.isDone());
//        try {
//            System.out.println(submit.get());
//        } catch (Exception e) {
//            e.getCause();
//        } finally {
//            executorService.shutdown();
//        }

        FutureTask<String> futureTask = new FutureTask<>(callable);
        Future<?> submit = executorService.submit(futureTask);

        try {
            Thread.sleep(2000);
//            futureTask.cancel(false);
//            futureTask.cancel(true);

            submit.cancel(true);
        } catch (InterruptedException e) {
            System.out.println("main be interrupted");
        }

        try {
            System.out.println("-------");
            futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
             executorService.shutdown();
        }


        /*try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public static void main3(String[] args) {

        PassBall ball = new PassBall(5);
        ball.pass();

        Scanner scan = new Scanner(System.in);

        while (true) {
            if (ball.stopFlag.get()) {
                break;
            }

            if (scan.hasNext()) {
                String str1 = scan.next();
                try {
                    System.out.println("int put : " + str1);
                    Integer integer = Integer.valueOf(str1);
                    ball.setStop(integer);
                } catch (Exception e) {
                    e.getMessage();
                }

            }
        }
        scan.close();
    }


    static class PassBall {
        AtomicInteger stop = new AtomicInteger(1);
        AtomicBoolean stopFlag = new AtomicBoolean(false);
        Semaphore semaphore = new Semaphore(1);
        ExecutorService executor;
        int allPlayerNum;

        public PassBall(int allPlayerNum) {
            this.allPlayerNum = allPlayerNum;
            executor = Executors.newFixedThreadPool(allPlayerNum);
        }

        void setStop(int stopNum) {
            stop.set(stopNum);
        }

        void pass() {
            Random random = new Random(3);
            for (int i = 0; i < allPlayerNum; i++)
                executor.submit(() -> {
                    while (true) {
                        try {
                            semaphore.acquire();
                            if (!gameIsStopped()) {
                                int nextInt = random.nextInt(3);
                                System.out.println(Thread.currentThread().getName() + " hold the ball " + nextInt + " seconds");
                                TimeUnit.SECONDS.sleep(nextInt);
                                System.out.println(Thread.currentThread().getName() + " pass the ball ");
                            } else {
                                System.out.println(Thread.currentThread().getName() + " the game has stopped");
                                break;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            semaphore.release();
                            try {
                                Thread.sleep(0);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            System.out.println(" -----------");

        }

        private boolean gameIsStopped() {
            int newValue = stop.incrementAndGet();
            System.out.println(" the count is " + newValue);
            if (newValue >= 100) {
                stopFlag.set(true);
                System.out.println(Thread.currentThread().getName() + " stop the game ");
                executor.shutdown();
                return true;
            } else {
                System.out.println(Thread.currentThread().getName() + " get the ball ");
                return false;
            }
        }
    }


    public static void main2(String[] args) {

        App app = new App();
        try {
            String showSearch = app.showSearch("hi....");
            System.out.println("waiting the result");
            System.out.println(showSearch);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    interface ArchiveSearcher {
        String search(String target);
    }

    static class App {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ArchiveSearcher searcher = new ArchiveSearcher() {
            @Override
            public String search(String target) {
                return target + " ------- ";
            }
        };

        String showSearch(final String target)
                throws InterruptedException {
            Future<String> future
                    = executor.submit(new Callable<String>() {
                public String call() throws InterruptedException {
                    System.out.println("excuting .... ");
                    TimeUnit.SECONDS.sleep(3);
                    return searcher.search(target);
                }
            });
            displayOtherThings(); // do other things while searching

            try {
                return future.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
            } finally {
                executor.shutdown();
            }
        }

        private void displayOtherThings() {
            System.out.println(" do other things ");
        }
    }
}
