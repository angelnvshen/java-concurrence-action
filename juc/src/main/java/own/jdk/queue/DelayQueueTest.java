package own.jdk.queue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 一个使用优先级队列实现的无界阻塞队列。
 * <p>
 * 延迟阻塞队列就是在阻塞队列的基础上提供了延迟获取任务的功能。
 */
public class DelayQueueTest {

    private static DelayQueue<DelayTask> delayQueue = new DelayQueue<>();

    static class DelayTask implements Delayed {

        // 延迟时间
        private final long delay;
        // 到期时间
        private final long expire;
        // 数据
        private final String msg;
        // 创建时间
        private final long now;

        /**
         * 初始化 DelayTask 对象
         *
         * @param delay 延迟时间 单位：微妙
         * @param msg   业务信息
         */
        DelayTask(long delay, String msg) {
            this.delay = delay;
            this.msg = msg;
            this.now = Instant.now().toEpochMilli();
            expire = now + delay; // 到期时间 = 当前时间+延迟时间
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(expire - Instant.now().toEpochMilli(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (o == this) // compare zero ONLY if same object
                return 0;
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "DelayTask{" +
                    "delay=" + delay +
                    ", expire=" + expire +
                    ", msg='" + msg + '\'' +
                    ", now=" + now +
                    '}';
        }
    }

    /**
     * 生产者线程
     *
     * @param args
     */
    public static void main(String[] args) {
        // System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS));
        initConsumer();

        try {
            // 等待消费者初始化完毕
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        delayQueue.add(new DelayTask(1000, "Task1"));
        delayQueue.add(new DelayTask(2000, "Task2"));
        delayQueue.add(new DelayTask(3000, "Task3"));
        delayQueue.add(new DelayTask(4000, "Task4"));
        delayQueue.add(new DelayTask(5000, "Task5"));
    }


    /**
     * 初始化消费者线程
     */
    private static void initConsumer() {
        Runnable task = () -> {
            while (true) {
                try {
                    System.out.println("尝试获取延迟队列中的任务。" + LocalDateTime.now());
                    System.out.println(delayQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread consumer = new Thread(task);
        consumer.start();
    }
}
