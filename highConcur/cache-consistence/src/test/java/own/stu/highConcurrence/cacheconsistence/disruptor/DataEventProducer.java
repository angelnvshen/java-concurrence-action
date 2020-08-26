package own.stu.highConcurrence.cacheconsistence.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class DataEventProducer {

    private RingBuffer<DataEvent> ringBuffer;

    public DataEventProducer(RingBuffer<DataEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }


    public void sendData(ByteBuffer buffer) {
        long sequence = ringBuffer.next();
        try {
            DataEvent dataEvent = ringBuffer.get(sequence);
            dataEvent.setValue(buffer.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
