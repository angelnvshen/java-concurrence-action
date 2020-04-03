package own.stu.netty.lecture.action.chat_two.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import own.stu.netty.lecture.action.chat_two.protocal.command.Packet;
import own.stu.netty.lecture.action.chat_two.protocal.command.PacketCodeC;
import own.stu.netty.lecture.action.chat_two.protocal.request.LoginRequestPacket;
import own.stu.netty.lecture.action.chat_two.protocal.request.MessageRequestPacket;
import own.stu.netty.lecture.action.chat_two.protocal.response.LoginResponsePacket;
import own.stu.netty.lecture.action.chat_two.protocal.response.MessageResponsePacket;
import own.stu.netty.lecture.action.chat_two.util.LoginUtil;

import java.nio.charset.Charset;
import java.util.Date;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginRequestPacket) {

            System.out.println(new Date() + ": 客户端开始登录……");

            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            } else {
                loginResponsePacket.setMessage("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }

            // 登录响应
            ctx.channel().writeAndFlush(PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket));

        } else if (packet instanceof MessageRequestPacket) {
            // 处理消息
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);

            ctx.channel().writeAndFlush(responseByteBuf);
        }

    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
