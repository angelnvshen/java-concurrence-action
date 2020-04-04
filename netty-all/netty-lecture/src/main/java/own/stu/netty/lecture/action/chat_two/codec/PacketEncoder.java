package own.stu.netty.lecture.action.chat_two.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import own.stu.netty.lecture.action.chat_two.protocal.command.Packet;
import own.stu.netty.lecture.action.chat_two.protocal.command.PacketCodeC;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.INSTANCE.encode(out, msg);
    }
}
