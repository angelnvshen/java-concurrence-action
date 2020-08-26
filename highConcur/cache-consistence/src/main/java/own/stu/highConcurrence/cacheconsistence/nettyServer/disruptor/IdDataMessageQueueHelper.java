package own.stu.highConcurrence.cacheconsistence.nettyServer.disruptor;

import com.lmax.disruptor.*;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import own.stu.highConcurrence.cacheconsistence.nettyServer.disruptor.handler.IdMessageConsumer;
import own.stu.highConcurrence.cacheconsistence.nettyServer.disruptor.handler.IdMessageConsumerImpl4Server;
import own.stu.highConcurrence.cacheconsistence.nettyServer.model.IdDataWrapper;
import own.stu.highConcurrence.cacheconsistence.nettyServer.model.Result;

import java.util.List;

@Component
public class IdDataMessageQueueHelper extends BaseQueueHelper<Result, IdDataWrapper, IdMessageConsumerImpl4Server> implements InitializingBean {

    private static final int QUEUE_SIZE = 1024;

    @Autowired
    private List<IdMessageConsumer> idDataEventHandler;

    @Override
    protected int getQueueSize() {
        return QUEUE_SIZE;
    }

    @Override
    protected IdDataWrapperFactory eventFactory() {
        return new IdDataWrapperFactory();
    }

    @Override
    protected WorkHandler[] getHandler() {
        int size = idDataEventHandler.size();
        IdMessageConsumerImpl4Server[] paramEventHandlers = idDataEventHandler.toArray(new IdMessageConsumerImpl4Server[size]);
        return paramEventHandlers;
    }

    @Override
    protected WaitStrategy getStrategy() {
        return new BlockingWaitStrategy();
        //return new YieldingWaitStrategy();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.init();
    }

    public synchronized void publishEvent(Result data, ChannelHandlerContext ctx) {
        getRingBuffer().publishEvent(new EventTranslatorTwoArg<IdDataWrapper, ChannelHandlerContext, Result>() {
            public void translateTo(IdDataWrapper event, long sequence, ChannelHandlerContext warpper, Result data) {
                event.setValue(data);
                event.setCtx(ctx);
            }
        }, ctx, data);
    }
}