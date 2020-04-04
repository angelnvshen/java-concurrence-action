package own.stu.netty.lecture.action.chat_two.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import own.stu.netty.lecture.action.chat_two.protocal.request.MessageRequestPacket;
import own.stu.netty.lecture.action.chat_two.protocal.response.MessageResponsePacket;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {

        System.out.println(new Date() + ": 收到客户端消息: " + msg.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【" + msg.getMessage() + "】");

        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
