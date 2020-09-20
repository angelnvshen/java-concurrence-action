package own.stu.netty.rpcsim.client.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import own.stu.netty.rpcsim.common.bean.RpcRequest;
import own.stu.netty.rpcsim.common.bean.RpcResponse;
import own.stu.netty.rpcsim.common.codec.handler.RpcDecoder;
import own.stu.netty.rpcsim.common.codec.handler.RpcEncoder;
import own.stu.netty.rpcsim.common.codec.heartBeat.Beat;

import java.util.concurrent.TimeUnit;

public class RpcClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //Serializer serializer = ProtostuffSerializer.class.newInstance();
//        Serializer serializer = HessianSerializer.class.newInstance();
//        Serializer serializer = KryoSerializer.class.newInstance();
        ChannelPipeline cp = socketChannel.pipeline();
        cp.addLast(new IdleStateHandler(0, 0, Beat.BEAT_INTERVAL, TimeUnit.SECONDS));
        cp.addLast(new RpcEncoder(RpcRequest.class));
        // cp.addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0));
        cp.addLast(new RpcDecoder(RpcResponse.class));
        cp.addLast(workerGroup, new RpcClientHandler());
    }

    public RpcClientInitializer(EventLoopGroup workerGroup) {
        this.workerGroup = workerGroup;
    }

    private EventLoopGroup workerGroup;
}