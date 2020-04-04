package own.stu.netty.lecture.action.chat_two.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import own.stu.netty.lecture.action.chat_two.protocal.request.LoginRequestPacket;
import own.stu.netty.lecture.action.chat_two.protocal.response.LoginResponsePacket;
import own.stu.netty.lecture.action.chat_two.session.Session;
import own.stu.netty.lecture.action.chat_two.util.SessionUtil;

import java.util.Date;
import java.util.UUID;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        System.out.println(new Date() + ": 收到客户端登录请求……");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(msg.getVersion());
        if (valid(msg)) {
            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setUserId(randomUserId());
            loginResponsePacket.setUserName(msg.getUserName());
            System.out.println(new Date() + " -> " + loginResponsePacket.getUserName() + " : 登录成功!");
            SessionUtil.bindSession(
                    new Session(loginResponsePacket.getUserId(), loginResponsePacket.getUserName()), ctx.channel());
        } else {
            loginResponsePacket.setMessage("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
