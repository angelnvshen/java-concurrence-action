package own.stu.netty.lecture.serverAndCilent;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
    System.out.println(channelHandlerContext.channel().remoteAddress() + ", " + s);
    TimeUnit.MILLISECONDS.sleep(200);
    channelHandlerContext.writeAndFlush("from server : " + UUID.randomUUID());
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
