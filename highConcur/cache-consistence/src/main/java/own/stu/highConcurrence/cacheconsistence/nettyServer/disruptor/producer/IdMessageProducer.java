package own.stu.highConcurrence.cacheconsistence.nettyServer.disruptor.producer;

import com.lmax.disruptor.RingBuffer;
import io.netty.channel.ChannelHandlerContext;
import own.stu.highConcurrence.cacheconsistence.nettyServer.model.IdDataWrapper;
import own.stu.highConcurrence.cacheconsistence.nettyServer.model.Result;

public class IdMessageProducer {

    private String producerId;

    private RingBuffer<IdDataWrapper> ringBuffer;

    public IdMessageProducer(String producerId, RingBuffer<IdDataWrapper> ringBuffer) {
        this.producerId = producerId;
        this.ringBuffer = ringBuffer;
    }

    public void onData(Result result, ChannelHandlerContext ctx) {
        long sequence = ringBuffer.next();
        try {
            IdDataWrapper wapper = ringBuffer.get(sequence);
            wapper.setValue(result);
            wapper.setCtx(ctx);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
