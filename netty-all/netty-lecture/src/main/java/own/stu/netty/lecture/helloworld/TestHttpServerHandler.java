package own.stu.netty.lecture.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
    if (httpObject instanceof HttpRequest) {
      ByteBuf content = Unpooled.copiedBuffer("Hello wordl", CharsetUtil.UTF_8);
      DefaultHttpResponse defaultHttpResponse = new DefaultFullHttpResponse(
          HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

      defaultHttpResponse.headers()
          .add(HttpHeaderNames.CONTENT_TYPE, "text/plain")
          .add(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

      channelHandlerContext.writeAndFlush(defaultHttpResponse);
    }
  }
}
