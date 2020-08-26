package own.stu.highConcurrence.nettyClient;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;
import own.stu.highConcurrence.netty.config.TypeData;
import own.stu.highConcurrence.netty.model.Model;

/**
 * 编码器
 *
 * @author Administrator
 */
public class MsgPckEncode extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf buf)
            throws Exception {
        // TODO Auto-generated method stub
        MessagePack pack = new MessagePack();

        Model mode = (Model) msg;
        if ((int) TypeData.CUSTOMER == mode.getType()) {
            System.out.println(mode.getBody());
        }

        byte[] write = pack.write(msg);

        buf.writeBytes(write);

    }
}
