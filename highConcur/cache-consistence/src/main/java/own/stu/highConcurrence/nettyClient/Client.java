package own.stu.highConcurrence.nettyClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import own.stu.highConcurrence.netty.config.TypeData;
import own.stu.highConcurrence.netty.model.Model;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class Client {

    public static String host = "127.0.0.1";
    public static int port = 10010;

    private NioEventLoopGroup worker = new NioEventLoopGroup();

    private Bootstrap bootstrap;

    private Channel channel;

    private ChannelFuture cf;

//    private CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();

        client.start(host, port);

        client.sendData();
    }

    public ChannelFuture start(String host, Integer port) {
        bootstrap = new Bootstrap();

        try {
            bootstrap.group(worker)
                    .channel(NioSocketChannel.class)
                    //表示缓存区动态调配（自适应）
                    .option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
                    //缓存区 池化操作
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            // TODO Auto-generated method stub
                            ChannelPipeline pipeline = ch.pipeline();

                            pipeline.addLast(new IdleStateHandler(0, 0, 5));

                            pipeline.addLast(new MsgPckDecode());

                            pipeline.addLast(new MsgPckEncode());

                            pipeline.addLast(new Client3Handler(Client.this));
                        }
                    });

            //绑定端口，同步等等请求连接
            return doConnect(host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cf;
    }

    /**
     * 连接服务端 and 重连
     */
    protected ChannelFuture doConnect(String host, Integer port) {

        if (channel != null && channel.isActive()) {
            return cf;
        }

        try {
            this.cf = bootstrap.connect(host, port);

            //实现监听通道连接的方法
            cf.addListener(new ChannelFutureListener() {

                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {

                    if (channelFuture.isSuccess()) {
                        channel = channelFuture.channel();
//                        latch.countDown();
                        System.out.println("连接成功");
                    } else {
                        System.out.println("每隔2s重连....");
                        channelFuture.channel().eventLoop().schedule(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                doConnect(host, port);
                            }
                        }, 2, TimeUnit.SECONDS);
                    }
                }
            });

            this.cf.sync();
            System.err.println("Client connected...");

            //接下来就进行数据的发送, 但是首先我们要获取channel:
            this.channel = cf.channel();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cf != null && cf.isSuccess()) {
                log.info("client connect to " + host + " on port " + port + " ...");
            } else {
                log.error("Netty server start up Error!");
            }
        }
        return cf;
    }

    /**
     * 向服务端发送消息
     */
    private void sendData() throws InterruptedException {
//        latch.await();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 1000; i++) {

            if (channel != null && channel.isActive()) {
                //获取一个键盘扫描器
                String nextLine = sc.nextLine();
                Model model = new Model();

                model.setType(TypeData.CUSTOMER);

                model.setBody(nextLine);

                channel.writeAndFlush(model);
            }
        }
    }

    public void sendData(String msg) {

        if (channel != null && channel.isActive()) {
            //获取一个键盘扫描器
            Model model = new Model();

            model.setType(TypeData.CUSTOMER);

            model.setBody(msg);

            channel.writeAndFlush(model);
        }
    }

    public void close() {
        log.info("Shutdown Client...");
        //优雅停机
        if (channel != null) {
            channel.close();
        }
        worker.shutdownGracefully();
        if (channel != null) {
            channel.close();
        }
        log.info("Shutdown Client Success!");
    }
}