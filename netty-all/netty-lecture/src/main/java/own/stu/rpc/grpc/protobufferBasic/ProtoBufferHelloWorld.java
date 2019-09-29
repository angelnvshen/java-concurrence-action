package own.stu.rpc.grpc.protobufferBasic;

import com.google.protobuf.InvalidProtocolBufferException;
import own.stu.rpc.grpc.protobufferBasic.StudentProto.Student;

public class ProtoBufferHelloWorld {

  public static void main(String[] args) throws InvalidProtocolBufferException {
    Student student = Student
        .newBuilder()
        .setId(1).setName("qin").setAddress("上海")
        .build();

    byte[] bytes = student.toByteArray();

    Student student1 = Student.parseFrom(bytes);
    System.out.println(student1);
    System.out.println(student1.getAddress());
  }
}
