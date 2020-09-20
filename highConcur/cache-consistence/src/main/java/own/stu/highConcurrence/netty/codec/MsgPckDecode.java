package own.stu.highConcurrence.netty.codec;

import java.util.List;

import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import own.stu.highConcurrence.netty.model.Model;

/**
 * 解码器
 *
 * @author Administrator
 */
public class MsgPckDecode extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
                          List<Object> out) throws Exception {

        final byte[] array;

        final int length = msg.readableBytes();

        array = new byte[length];

        msg.getBytes(msg.readerIndex(), array, 0, length);

        MessagePack pack = new MessagePack();

        out.add(pack.read(array, Model.class));

    }
}