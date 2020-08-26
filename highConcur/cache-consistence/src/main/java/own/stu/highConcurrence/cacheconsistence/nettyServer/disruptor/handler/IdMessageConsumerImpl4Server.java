package own.stu.highConcurrence.cacheconsistence.nettyServer.disruptor.handler;

import io.netty.channel.ChannelHandlerContext;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import own.stu.highConcurrence.cacheconsistence.common.cache.GlobalCache;
import own.stu.highConcurrence.cacheconsistence.nettyServer.model.IdDataWrapper;
import own.stu.highConcurrence.cacheconsistence.nettyServer.model.Result;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class IdMessageConsumerImpl4Server extends IdMessageConsumer {

    @Autowired
    private GlobalCache cache;

    private static final AtomicInteger consumerId = new AtomicInteger(1);

    public IdMessageConsumerImpl4Server(String consumerId) {
        super(consumerId);
    }

    public IdMessageConsumerImpl4Server() {
        setConsumerId("c - " + consumerId.getAndIncrement());
    }

    public void onEvent(IdDataWrapper event) throws Exception {
        Result result = event.getValue();
        ChannelHandlerContext ctx = event.getCtx();
        //1.业务处理逻辑:
        System.out.println("Sever端: key= " + result.getKey());

        //2.回送响应信息:
        result.setId(cache.get(result.getKey()));
        result.setKey("resp: " + result.getKey());
        //写出response响应信息:
        ctx.writeAndFlush(result);
    }

}