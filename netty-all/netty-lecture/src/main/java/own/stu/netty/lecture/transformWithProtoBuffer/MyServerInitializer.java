package own.stu.netty.lecture.transformWithProtoBuffer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    ChannelPipeline pipeline = socketChannel.pipeline();

    // 注意顺序
    pipeline.addLast(new ProtobufVarint32FrameDecoder());
    //pipeline.addLast(new ProtobufDecoder(StudentNettyProto.Student.getDefaultInstance()));
    pipeline.addLast(new ProtobufDecoder(MultiDataInfo.MultiData.getDefaultInstance()));
    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
    pipeline.addLast(new ProtobufEncoder());

    pipeline.addLast(new MyServerSecHandler());
  }
}
