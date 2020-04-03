package own.stu.netty.lecture.action.chat_two.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import own.stu.netty.lecture.action.chat_two.protocal.command.Packet;
import own.stu.netty.lecture.action.chat_two.protocal.command.PacketCodeC;
import own.stu.netty.lecture.action.chat_two.protocal.request.LoginRequestPacket;
import own.stu.netty.lecture.action.chat_two.protocal.response.LoginResponsePacket;

import java.util.Date;
import java.util.UUID;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserId(UUID.randomUUID().toString());
        packet.setUserName("flash");
        packet.setPassword("pwd");

        // 编码 | 写数据
        ctx.channel().writeAndFlush(PacketCodeC.INSTANCE.encode(packet));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if (loginResponsePacket.isSuccess()) {
                System.out.println(new Date() + ": 客户端登录成功");
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getMessage());
            }
        }
    }
}
