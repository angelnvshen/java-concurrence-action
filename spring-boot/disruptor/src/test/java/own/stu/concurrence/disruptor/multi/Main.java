package own.stu.concurrence.disruptor.multi;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.Data;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        RingBuffer<Order> ringBuffer = RingBuffer.create(
                ProducerType.MULTI,
                () -> new Order(),
                1024,
//                new YieldingWaitStrategy()
                new BlockingWaitStrategy()
        );

        // 通过ringBuffer 创建一个屏障
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        Consumer[] consumers = new Consumer[3];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer("C-" + i);
        }

        // 构建多消费者工作池
        WorkerPool<Order> workerPool = new WorkerPool<>(
                ringBuffer,
                sequenceBarrier,
                getExceptionHandler(),
                consumers
        );

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        workerPool.start(executorService);


        CountDownLatch latch = new CountDownLatch(1);
        // 生产者
        for (int i = 0; i < 3; i++) {

            Producer producer = new Producer(ringBuffer);
            new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 10; j++) {
                    producer.sendData();
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(2); // 等待线程启动
        System.out.println("===============  produce data ============== ");
        latch.countDown();

        TimeUnit.SECONDS.sleep(3); // 等待线程执行完成
        System.out.println("消费总记录数 ： " + consumers[0].getCount());

        workerPool.halt();
        executorService.shutdown();
    }

    private static ExceptionHandler<Order> getExceptionHandler() {
        return new ExceptionHandler<Order>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, Order event) {

            }

            @Override
            public void handleOnStartException(Throwable ex) {

            }

            @Override
            public void handleOnShutdownException(Throwable ex) {

            }
        };
    }

    @Data
    static class Order {
        private static AtomicLong ids = new AtomicLong(0);

        private long id;
        private String name;

        public Order() {
            id = ids.incrementAndGet();
        }

        /*public void setId() {
            id = ids.getAndIncrement();
        }*/

    }

    static class Consumer implements WorkHandler<Order> {
        private String consumerId;

        private static AtomicLong count = new AtomicLong(0);

        public Consumer(String consumerId) {
            this.consumerId = consumerId;
        }

        @Override
        public void onEvent(Order event) throws Exception {
            System.out.println("消费者 " + consumerId + ", -> " + event);

            count.getAndIncrement();
        }

        public long getCount() {
            return count.get();
        }
    }

    static class Producer {
        private RingBuffer<Order> ringBuffer;

        private static AtomicInteger ids = new AtomicInteger(0);

        public Producer(RingBuffer<Order> ringBuffer) {
            this.ringBuffer = ringBuffer;
            producerId = ids.getAndIncrement();
        }

        private int producerId;

        public void sendData() {
            long next = ringBuffer.next();
            try {
                Order order = ringBuffer.get(next);
//                order.setId(UUID.randomUUID().toString());
                order.setName("producerId[ " + producerId + " ]iphone : " + order.getId());
            } finally {
                ringBuffer.publish(next);
            }
        }
    }
}
