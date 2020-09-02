package own.stu.netty.rpcsim.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import own.stu.netty.rpcsim.common.util.SerializationUtil;

public class RpcEncoder extends MessageToByteEncoder {

    private Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object t, ByteBuf out) throws Exception {
        if (!genericClass.isInstance(t)) {
            return;
        }
        byte[] bytes = SerializationUtil.serialize(t);

        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}
