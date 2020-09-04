package own.stu.netty.rpcsim.client.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import own.stu.netty.rpcsim.client.bean.RpcFuture;
import own.stu.netty.rpcsim.common.bean.RpcRequest;
import own.stu.netty.rpcsim.common.bean.RpcResponse;
import own.stu.netty.rpcsim.common.codec.heartBeat.Beat;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class RpcClientHandler extends SimpleChannelInboundHandler<RpcResponse> {

    private volatile Channel channel;

    private ConcurrentHashMap<String, RpcFuture> pendingRPC = new ConcurrentHashMap<>();

    private SocketAddress remotePeer;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.remotePeer = this.channel.remoteAddress();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.channel = ctx.channel();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) throws Exception {
        String requestId = rpcResponse.getRequestId();
        log.debug("Receive response: " + requestId);
        RpcFuture rpcFuture = pendingRPC.get(requestId);
        if (rpcFuture != null) {
            pendingRPC.remove(requestId);
            rpcFuture.done(rpcResponse);
        } else {
            log.warn("Can not get pending response for request id: " + requestId);
        }
    }

    public RpcFuture sendRequest(RpcRequest request) throws Exception {
        RpcFuture rpcFuture = new RpcFuture(request);
        pendingRPC.put(request.getRequestId(), rpcFuture);

        ChannelFuture channelFuture = channel.writeAndFlush(request).sync();
        if (!channelFuture.isSuccess()) {
            log.error("Send request {} error", request.getRequestId());
        }

        return rpcFuture;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("api caught exception", cause);
        //ctx.close();
    }

    public void close() {
        System.out.println(" ===========  ");
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public static String remoteAddr(InetSocketAddress socketAddress) {
        return socketAddress.getHostString() + ":" + socketAddress.getPort();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            //Send ping
            sendRequest(Beat.BEAT_PING());
            log.debug("Client send beat-ping to " + remotePeer);
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
