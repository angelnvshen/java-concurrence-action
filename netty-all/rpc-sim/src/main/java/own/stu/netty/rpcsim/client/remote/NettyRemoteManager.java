package own.stu.netty.rpcsim.client.remote;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import own.stu.netty.rpcsim.client.handler.RpcClientHandler;
import own.stu.netty.rpcsim.client.handler.RpcClientInitializer;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class NettyRemoteManager {

    private NettyRemoteManager() {
    }

    private static final NettyRemoteManager INSTANCE = new NettyRemoteManager();

    public static NettyRemoteManager getInstance() {
        return INSTANCE;
    }

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8,
            600L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000));

    private EventLoopGroup workerGroup = new NioEventLoopGroup(4);

    private ConcurrentHashMap<String, RpcClientHandler> connectedServerNodes = new ConcurrentHashMap<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition connected = lock.newCondition();
    private long waitTimeout = 5000;

    private volatile boolean isRunning = true;

    public void clearServerNode(String serviceAddr) {
        if (StringUtils.isEmpty(serviceAddr))
            return;
        connectedServerNodes.remove(serviceAddr);
    }

    public void connectServerNode(String host, int port) {
        final InetSocketAddress remotePeer = new InetSocketAddress(host, port);
        threadPoolExecutor.submit(new NettyClientTask(remotePeer));
    }

    public RpcClientHandler chooseHandler(String address) throws Exception {
        int size = connectedServerNodes.values().size();
        while (isRunning && size <= 0) {
            try {
                waitingForHandler();
                size = connectedServerNodes.values().size();
            } catch (InterruptedException e) {
                log.error("Waiting for available service is interrupted!", e);
            }
        }
        RpcClientHandler handler = connectedServerNodes.get(address);
        if (handler != null) {
            return handler;
        } else {
            throw new Exception("Can not get available connection");
        }
    }

    private void signalAvailableHandler() {
        lock.lock();
        try {
            connected.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private boolean waitingForHandler() throws InterruptedException {
        lock.lock();
        try {
            log.warn("Waiting for available service");
            return connected.await(this.waitTimeout, TimeUnit.MILLISECONDS);
        } finally {
            lock.unlock();
        }
    }


    public void stop() {
        isRunning = false;

        Iterator<RpcClientHandler> iterator = connectedServerNodes.values().iterator();
        while (iterator.hasNext()) {
            RpcClientHandler handler = iterator.next();
            if (handler != null) {
                handler.close();
            }
            iterator.remove();
        }
        workerGroup.shutdownGracefully();
        threadPoolExecutor.shutdown();
    }

    class NettyClientTask implements Runnable {

        private InetSocketAddress remotePeer;

        public NettyClientTask(InetSocketAddress remotePeer) {
            this.remotePeer = remotePeer;
        }

        @Override
        public void run() {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new RpcClientInitializer());

            ChannelFuture channelFuture = b.connect(remotePeer);

            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(final ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        log.info("Successfully connect to remote server, remote peer = " + remotePeer);
                        RpcClientHandler handler = channelFuture.channel().pipeline().get(RpcClientHandler.class);
                        connectedServerNodes.put(RpcClientHandler.remoteAddr(remotePeer), handler);
                        signalAvailableHandler();
                    } else {
                        log.error("Can not connect to remote server, remote peer = " + remotePeer);
                    }
                }
            });
        }
    }


}
