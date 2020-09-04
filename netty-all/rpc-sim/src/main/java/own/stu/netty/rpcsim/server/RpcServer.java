package own.stu.netty.rpcsim.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;
import own.stu.netty.rpcsim.common.bean.RpcRequest;
import own.stu.netty.rpcsim.common.bean.RpcResponse;
import own.stu.netty.rpcsim.common.codec.handler.RpcDecoder;
import own.stu.netty.rpcsim.common.codec.handler.RpcEncoder;
import own.stu.netty.rpcsim.common.codec.heartBeat.Beat;
import own.stu.netty.rpcsim.registry.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RpcServer implements ApplicationContextAware, InitializingBean {

    private ServiceRegistry serviceRegistry;

    private String serviceAddress;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Channel channel;
    private ChannelFuture channelFuture;

    /**
     * 存放 服务名 与 服务对象 之间的映射关系
     */
    private Map<String, Object> handlerMap = new HashMap<>();

    public RpcServer(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public RpcServer(String serviceAddress, ServiceRegistry serviceRegistry) {
        this.serviceAddress = serviceAddress;
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        try {
            // 创建并初始化 Netty 服务端 Bootstrap 对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new IdleStateHandler(0, 0, Beat.BEAT_TIMEOUT, TimeUnit.SECONDS));
                    pipeline.addLast(new RpcDecoder(RpcRequest.class)); // 解码 RPC 请求
                    pipeline.addLast(new RpcEncoder(RpcResponse.class)); // 编码 RPC 响应
                    pipeline.addLast(new RpcServerHandler(handlerMap)); // 处理 RPC 请求
                }
            });
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

            // 获取 RPC 服务器的 IP 地址与端口号
            String[] addressArray = serviceAddress.split(":");
            String ip = addressArray[0];
            int port = Integer.parseInt(addressArray[1]);

            // 启动 RPC 服务器
            channelFuture = bootstrap.bind(ip, port).sync();

            // 注册 RPC 服务地址
            if (serviceRegistry != null) {
                serviceRegistry.register(handlerMap.keySet(), serviceAddress);
                log.debug("register service: {} => {}", handlerMap.keySet(), serviceAddress);
            }

            log.debug("server started on port {}", port);

            // 关闭 RPC 服务器
            channel = channelFuture.channel();
            //channel.closeFuture().sync();
            channel.closeFuture().syncUninterruptibly();
        } catch (Exception e) {
            e.printStackTrace();
            if (workerGroup != null)
                workerGroup.shutdownGracefully();
            if (bossGroup != null)
                bossGroup.shutdownGracefully();
        }
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 扫描带有 RpcService 注解的类并初始化 handlerMap 对象
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);

        if (serviceBeanMap.isEmpty()) {
            return;
        }

        for (Object serviceBean : serviceBeanMap.values()) {
            RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
            String serviceName = rpcService.value().getName();
            String serviceVersion = rpcService.version();
            if (!StringUtils.isEmpty(serviceVersion)) {
                serviceName += "-" + serviceVersion;
            }
            handlerMap.put(serviceName, serviceBean);
        }
    }
}
