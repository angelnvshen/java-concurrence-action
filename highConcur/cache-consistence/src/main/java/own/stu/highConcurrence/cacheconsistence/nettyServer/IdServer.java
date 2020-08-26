package own.stu.highConcurrence.cacheconsistence.nettyServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import own.stu.highConcurrence.cacheconsistence.nettyServer.channelHandler.ServerHandler;
import own.stu.highConcurrence.netty.codec.MarshallingCodeCFactory;

@Slf4j
@Component
public class IdServer {

    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

    private Channel channel = null;

//    @Autowired
//    private ServerHandler serverHandler;

    @Autowired
    private ApplicationContext applicationContext;

    public ChannelFuture start(String hostname, Integer port) {

        ChannelFuture channelFuture = null;

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(hostname, port)
                    .option(ChannelOption.SO_BACKLOG, 1024)//连接数 syn + accept
                    // .option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT) //缓冲区动态调整，自适应
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT) // 缓冲区 池化操作
                    .handler(new LoggingHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(MarshallingCodeCFactory.buildMarshallingDecoder())
                                    .addLast(MarshallingCodeCFactory.buildMarshallingEncoder())
                                    .addLast(applicationContext.getBean("serverHandler", ServerHandler.class));

                        }
                    });
            channelFuture = bootstrap.bind().sync();

            channel = channelFuture.channel();
            log.info("======EchoServer启动成功!!!=========");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channelFuture != null && channelFuture.isSuccess()) {
                log.info("Netty server listening " + hostname + " on port " + port + " and ready for connections...");
            } else {
                log.error("Netty server start up Error!");
            }
        }
        return channelFuture;
    }

    /**
     * 停止服务
     */
    public void destroy() {
        log.info("Shutdown Netty Server...");
        if (channel != null) {
            channel.close();
        }
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        log.info("Shutdown Netty Server Success!");
    }
}
