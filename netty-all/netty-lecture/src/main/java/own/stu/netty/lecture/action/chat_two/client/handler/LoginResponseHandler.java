package own.stu.netty.lecture.action.chat_two.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import own.stu.netty.lecture.action.chat_two.protocal.response.LoginResponsePacket;
import own.stu.netty.lecture.action.chat_two.session.Session;
import own.stu.netty.lecture.action.chat_two.util.SessionUtil;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        String userId = msg.getUserId();
        String userName = msg.getUserName();

        if (msg.isSuccess()) {
            System.out.println("[" + userName + "]登录成功，userId 为: " + msg.getUserId());
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            System.out.println("[" + userName + "]登录失败，原因：" + msg.getMessage());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭!");
    }
}
