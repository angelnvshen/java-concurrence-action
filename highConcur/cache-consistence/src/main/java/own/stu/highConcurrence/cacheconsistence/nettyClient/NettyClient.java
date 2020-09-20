package own.stu.highConcurrence.cacheconsistence.nettyClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import own.stu.highConcurrence.cacheconsistence.nettyServer.model.RequestData;
import own.stu.highConcurrence.cacheconsistence.nettyServer.model.Result;
import own.stu.highConcurrence.netty.codec.MarshallingCodeCFactory;

import java.util.concurrent.TimeUnit;

public class NettyClient {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 10010;

    //扩展 完善 池化: ConcurrentHashMap<KEY -> String, Value -> Channel>
    private Channel channel;

    //1. 创建工作线程组: 用于实际处理业务的线程组
    private EventLoopGroup workGroup = new NioEventLoopGroup();

    private ChannelFuture cf;

    public NettyClient() {
        this.connect(HOST, PORT);
    }

    private void connect(String host, int port) {
        //2 辅助类(注意Client 和 Server 不一样)
        Bootstrap bootstrap = new Bootstrap();
        try {

            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    //表示缓存区动态调配（自适应）
                    //.option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
                    //缓存区 池化操作
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                            sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                            sc.pipeline().addLast(new ClientHandler());
                        }
                    });
            //绑定端口，同步等等请求连接
            this.cf = bootstrap.connect(host, port).sync();
            System.err.println("Client connected...");

            //接下来就进行数据的发送, 但是首先我们要获取channel:
            this.channel = cf.channel();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendData() {

        for (int i = 0; i < 10; i++) {
            RequestData<String> request = new RequestData();
            request.setData("order");
            this.channel.writeAndFlush(request);
        }
    }

    public void close() throws Exception {
        cf.channel().closeFuture().sync();
        //优雅停机
        workGroup.shutdownGracefully();
        System.err.println("Sever ShutDown...");
    }

    public static void main(String[] args) throws InterruptedException {
        NettyClient client = new NettyClient();
        TimeUnit.MILLISECONDS.sleep(10);
        if (client.channel != null && client.channel.isActive())
            client.sendData();
    }
}
