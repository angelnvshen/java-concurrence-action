package own.stu.netty.lecture.transformWithProtoBuffer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Objects;
import own.stu.netty.lecture.transformWithProtoBuffer.MultiDataInfo.Cat;
import own.stu.netty.lecture.transformWithProtoBuffer.MultiDataInfo.Dog;
import own.stu.netty.lecture.transformWithProtoBuffer.MultiDataInfo.MultiData;
import own.stu.netty.lecture.transformWithProtoBuffer.MultiDataInfo.MultiData.DataType;
import own.stu.netty.lecture.transformWithProtoBuffer.MultiDataInfo.Student;

public class MyServerSecHandler extends SimpleChannelInboundHandler<MultiDataInfo.MultiData> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, MultiData multiData) throws Exception {
//    multiData.getDataType()

    if (Objects.equals(multiData.getDataType(), DataType.StudentType)) {
      Student student = multiData.getStudent();
      System.out.println(student.getId());
      System.out.println(student.getName());
      System.out.println(student.getAddress());
    }

    if (Objects.equals(multiData.getDataType(), DataType.CatType)) {
      Cat cat = multiData.getCat();
      System.out.println(cat.getName());
    }

    if (Objects.equals(multiData.getDataType(), DataType.DogType)) {
      Dog dog = multiData.getDog();
      System.out.println(dog.getName());
    }
  }
}
