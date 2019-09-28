package own.stu.netty.lecture.serverAndCilent;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
    System.out.println(channelHandlerContext.channel().remoteAddress() + " -> " + s);
    TimeUnit.MILLISECONDS.sleep(100);
    channelHandlerContext.writeAndFlush("from client : " + LocalDateTime.now());
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    ctx.writeAndFlush("来自客户端的问候 ~");
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
