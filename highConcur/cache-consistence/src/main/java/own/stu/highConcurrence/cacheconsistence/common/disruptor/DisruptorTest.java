package own.stu.highConcurrence.cacheconsistence.common.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisruptorTest {

    private static final int ring_buffer_size = 1024 * 1024;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Disruptor<DataEvent> disruptor = new Disruptor<>(
                new DataEventFactory(),
                ring_buffer_size,
                executorService,
                ProducerType.SINGLE,
                new BlockingWaitStrategy()
        );

        // 添加消费者的监听
        disruptor.handleEventsWith(new DataEventHandler());

        disruptor.start();

        RingBuffer<DataEvent> ringBuffer = disruptor.getRingBuffer();

        DataEventProducer dataEventProducer = new DataEventProducer(ringBuffer);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        for (int i = 0; i < 100; i++) {
            buffer.putLong(0, i);
            dataEventProducer.sendData(buffer);
        }

        disruptor.shutdown();
        executorService.shutdown();
    }
}
