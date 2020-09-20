package own.stu.highConcurrence.cacheconsistence.nettyServer.model;

import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import own.stu.highConcurrence.disruptor.model.ValueWrapper;

@Data
public class IdDataWrapper extends ValueWrapper<Result> {

    private String key;

    private ChannelHandlerContext ctx;
}
