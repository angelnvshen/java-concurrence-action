package own.stu.netty.rpcsim.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.util.StringUtils;
import own.stu.netty.rpcsim.common.bean.RpcRequest;
import own.stu.netty.rpcsim.common.bean.RpcResponse;
import own.stu.netty.rpcsim.common.codec.heartBeat.Beat;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * RPC 服务端处理器（用于处理 RPC 请求）
 */
@Slf4j
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private final Map<String, Object> handlerMap;

    public RpcServerHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest rpcRequest) throws Exception {

        // filter beat ping
        if (Beat.BEAT_ID.equalsIgnoreCase(rpcRequest.getRequestId())) {
            log.info("Server read heartbeat ping");
            return;
        }

        // 创建并初始化 RPC 响应对象
        RpcResponse response = new RpcResponse();
        response.setRequestId(rpcRequest.getRequestId());

        try {
            Object result = handle(rpcRequest);
            response.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("handle result failure", e);
            response.setException(e);
        }

        // 写入 RPC 响应对象

        ctx.writeAndFlush(response)
                .addListener((ChannelFutureListener) channelFuture ->
                        log.info("Send response for request " + rpcRequest.getRequestId()));
    }

    private Object handle(RpcRequest request) throws InvocationTargetException {

        // 获取服务对象
        String serviceName = request.getInterfaceName();
        String serviceVersion = request.getServiceVersion();
        if (!StringUtils.isEmpty(serviceVersion)) {
            serviceName += "-" + serviceVersion;
        }
        Object serviceBean = handlerMap.get(serviceName);

        if (serviceBean == null) {
            System.out.println(request);
            throw new RuntimeException(String.format("can not find service bean by key: %s", serviceName));
        }

        // 获取反射调用所需的参数
        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        // 执行反射调用
//        Method method = serviceClass.getMethod(methodName, parameterTypes);
//        method.setAccessible(true);
//        return method.invoke(serviceBean, parameters);

        // 使用 CGLib 执行反射调用
        FastClass serviceFastClass = FastClass.create(serviceClass);
        FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
        return serviceFastMethod.invoke(serviceBean, parameters);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("server caught exception", cause);
        // ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            ctx.channel().close();
            log.warn("Channel idle in last {} seconds, close it", Beat.BEAT_TIMEOUT);
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
