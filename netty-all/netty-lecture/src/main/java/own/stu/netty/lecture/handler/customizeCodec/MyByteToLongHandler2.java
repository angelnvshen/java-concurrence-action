package own.stu.netty.lecture.handler.customizeCodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongHandler extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("my decoder invoked : " + in + "| " + in.readableBytes());
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
