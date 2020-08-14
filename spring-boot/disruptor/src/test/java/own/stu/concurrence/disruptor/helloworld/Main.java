package own.stu.concurrence.disruptor.helloworld;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.Data;

import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 框架的辅助类
        Disruptor<Order> disruptor = new Disruptor<>(
                new OrderEventFactory(),
                4,
                executorService,
                ProducerType.SINGLE,
                new BlockingWaitStrategy());

        // 设置一个消息者
        disruptor.handleEventsWith(new OrderEventHandler());

        // 启动
        disruptor.start();

        RingBuffer<Order> ringBuffer = disruptor.getRingBuffer();

        // 生产者
        OrderEventProducer producer = new OrderEventProducer(ringBuffer);

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 0; i < 10; i++) {
            byteBuffer.putLong(0, i);
            producer.sendData(byteBuffer);
        }

        disruptor.shutdown();
        executorService.shutdown();
    }

    @Data
    static class Order {
        private static AtomicLong idGenerator = new AtomicLong(0);

        private Long orderId;

        public Order() {
        }

        public void setOrderId() {
            this.orderId = idGenerator.getAndIncrement();
        }
    }

    static class OrderEventFactory implements EventFactory<Order> {

        @Override
        public Order newInstance() {
            return new Order();
        }
    }

    private static class OrderEventHandler implements EventHandler<Order> {
        @Override
        public void onEvent(Order event, long sequence, boolean endOfBatch) throws Exception {
//            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            System.out.println("sequence : " + sequence + ", event : " + event.getOrderId());
        }
    }

    private static class OrderEventProducer {

        private RingBuffer<Order> ringBuffer;

        public OrderEventProducer(RingBuffer<Order> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        public void sendData(ByteBuffer byteBuffer) {
            long next = ringBuffer.next();
            try {
                Order order = ringBuffer.get(next);
                long orderId = byteBuffer.getLong(0);
                order.setOrderId(orderId);
            } finally {
                ringBuffer.publish(next);
            }
        }
    }
}
