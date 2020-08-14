package own.stu.concurrence.disruptor.height;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService handlerExecutors = Executors.newFixedThreadPool(5);

        Disruptor<Trade> disruptor = new Disruptor<Trade>(
                () -> new Trade(), // EventFactory
                4,
                handlerExecutors,
                ProducerType.SINGLE,
                new BusySpinWaitStrategy()
        );

        // 菱形运算
        disruptor.handleEventsWith(Main::handleEvent_0)
                .then(Main::handleEvent_2, Main::handleEvent_3)
                .then(Main::handleEvent_1);

        // 启动
        disruptor.start();

        RingBuffer<Trade> ringBuffer = disruptor.getRingBuffer();
        CountDownLatch latch = new CountDownLatch(1);
        TradeEventProduct tradeEventProduct = new TradeEventProduct(ringBuffer, latch);
        tradeEventProduct.sendData();

        latch.await();
        disruptor.shutdown();
        handlerExecutors.shutdown();

    }

    private static void handleEvent_0(Trade event, long sequence, boolean endOfBatch) throws Exception {
        event.setId();
        event.setPrice(17d);
        event.setName("iphone -> " + sequence);
        System.out.println("h0 ->  sequence : " + sequence + " , trade " + event);
    }

    private static void handleEvent_1(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("h1 ->  sequence : " + sequence + " , trade " + event);
    }

    private static void handleEvent_2(Trade event, long sequence, boolean endOfBatch) throws Exception {
        event.setName(event.getName() + " | de");
    }

    private static void handleEvent_3(Trade event, long sequence, boolean endOfBatch) throws Exception {
        event.setPrice(event.getPrice() + 3);
    }

    @Data
    static class TradeEventProduct {
        private RingBuffer<Trade> ringBuffer;
        private CountDownLatch latch;

        private static int PUBLISH_COUNT = 10;

        public TradeEventProduct(RingBuffer<Trade> ringBuffer, CountDownLatch latch) {
            this.ringBuffer = ringBuffer;
            this.latch = latch;
        }

        public void sendData() {
            for (int i = 0; i < PUBLISH_COUNT; i++)
                ringBuffer.publishEvent(((event, sequence) -> {
                    event.setId();
                    event.setName("");
                    event.setPrice(0d);
                }));
            latch.countDown();
        }
    }

    @Data
    static class Trade {

        private static AtomicLong ids = new AtomicLong(0);

        private Long id;
        private String name;
        private double price;

        public void setId() {
            id = ids.getAndIncrement();
        }
    }
}
