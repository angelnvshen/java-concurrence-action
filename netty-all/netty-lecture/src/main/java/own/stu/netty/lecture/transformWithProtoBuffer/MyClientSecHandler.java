package own.stu.netty.lecture.transformWithProtoBuffer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Random;
import own.stu.netty.lecture.transformWithProtoBuffer.MultiDataInfo.Cat;
import own.stu.netty.lecture.transformWithProtoBuffer.MultiDataInfo.Dog;
import own.stu.netty.lecture.transformWithProtoBuffer.MultiDataInfo.MultiData;
import own.stu.netty.lecture.transformWithProtoBuffer.MultiDataInfo.MultiData.DataType;
import own.stu.netty.lecture.transformWithProtoBuffer.MultiDataInfo.Student;

public class MyClientSecHandler extends SimpleChannelInboundHandler<MultiData> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, MultiData multiData) throws Exception {

  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    MultiData multiData = null;
    int random = new Random().nextInt(3);
    if (random == 0) {

      Student student = Student.newBuilder().setAddress("孔康").setName("ijq").setId(3)
          .build();

      multiData = MultiData.newBuilder()
          .setDataType(DataType.StudentType)
          .setStudent(student)
          .build();

    } else if (random == 1) {
      Cat dog = Cat.newBuilder().setName("猫啊")
          .build();

      multiData = MultiData.newBuilder()
          .setDataType(DataType.CatType)
          .setCat(dog)
          .build();

    } else {
      Dog dog = Dog.newBuilder().setName("狗啊")
          .build();

      multiData = MultiData.newBuilder()
          .setDataType(DataType.DogType)
          .setDog(dog)
          .build();
    }

    ctx.channel().writeAndFlush(multiData);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
