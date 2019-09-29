package own.stu.netty.lecture.transformWithProtoBuffer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import own.stu.netty.lecture.transformWithProtoBuffer.StudentNettyProto.Student;

public class MyServerHandler extends SimpleChannelInboundHandler<StudentNettyProto.Student> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, Student student) throws Exception {
    System.out.println(student.getId());
    System.out.println(student.getName());
    System.out.println(student.getAddress());
  }
}
