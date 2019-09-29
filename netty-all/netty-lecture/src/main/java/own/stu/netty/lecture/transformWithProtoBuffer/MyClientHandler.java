package own.stu.netty.lecture.transformWithProtoBuffer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import own.stu.netty.lecture.transformWithProtoBuffer.StudentNettyProto.Student;

public class MyClientHandler extends SimpleChannelInboundHandler<StudentNettyProto.Student> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, Student student) throws Exception {

  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    Student student = Student
        .newBuilder()
        .setAddress("北京").setId(2).setName("YAA")
        .build();
    ctx.channel().writeAndFlush(student);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
