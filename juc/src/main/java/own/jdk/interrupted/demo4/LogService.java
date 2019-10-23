package own.jdk.interrupted.demo4;

import javax.annotation.concurrent.GuardedBy;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 任务取消 之 停止基于线程的服务
 * 多个生产者，单个消费者模式
 */
public class LogService {


    private final BlockingQueue<String> queue;
    private final LogThread logThread;
    private final PrintWriter writer;

    @GuardedBy("this")
    private int reservations;

    @GuardedBy("this")
    private boolean shutdown;

    public LogService(Writer writer) {
        this.queue = new LinkedBlockingQueue<>();
        this.logThread = new LogThread();
        this.writer = new PrintWriter(writer);
    }

    public void start() {
        logThread.start();
    }

    public void stop() {
        synchronized (this) {
            shutdown = true;
        }
        logThread.interrupt();
    }

    public void log(String msg) {
        synchronized (this) {
            if (shutdown) {
                throw new IllegalStateException();
            }
            ++reservations;
        }
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消费日志线程
     */
    private class LogThread extends Thread {

        @Override
        public void run() {

            while (true) {
                try {
                    synchronized (this) {
                        if (shutdown && reservations <= 0) {
                            writer.close();
                            break;
                        }
                    }

                    String message = queue.take();
                    synchronized (this) {
                        --reservations;
                    }
                    writer.println(message);
                    writer.flush();
                } catch (Exception e) {
                    // retry. 执行完所有任务后stop
                    if (e instanceof InterruptedException) {
                        System.out.println("InterruptedException .... ");
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        LogService logService = new LogService(new OutputStreamWriter(System.out));
        logService.start();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Random random = new Random(100);
        for (int i = 0; i < 4; i++)
            executorService.submit(() -> {
                for (int j = 0; j < 100; j++)
                    logService.log(Thread.currentThread().getName() + " ： " + random.nextInt(1000));
            });


        TimeUnit.SECONDS.sleep(2);
        logService.stop();
        executorService.shutdown();
    }
}
