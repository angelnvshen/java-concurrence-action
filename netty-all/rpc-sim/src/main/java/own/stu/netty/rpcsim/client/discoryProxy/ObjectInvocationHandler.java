package own.stu.netty.rpcsim.client.discoryProxy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import own.stu.netty.rpcsim.client.bean.RpcFuture;
import own.stu.netty.rpcsim.client.handler.RpcClientHandler;
import own.stu.netty.rpcsim.client.remote.NettyRemoteManager;
import own.stu.netty.rpcsim.common.bean.RpcRequest;
import own.stu.netty.rpcsim.common.bean.RpcResponse;
import own.stu.netty.rpcsim.registry.ServiceDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

@Slf4j
public class ObjectInvocationHandler<T> implements InvocationHandler {

    private Class<T> interfaceClass;
    private String serviceVersion;

    private ServiceDiscovery serviceDiscovery;

    public ObjectInvocationHandler(Class<T> interfaceClass, String serviceVersion, ServiceDiscovery serviceDiscovery) {
        this.interfaceClass = interfaceClass;
        this.serviceVersion = serviceVersion;
        this.serviceDiscovery = serviceDiscovery;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 创建 RPC 请求对象并设置请求属性
        RpcRequest request = new RpcRequest();
        request.setRequestId(UUID.randomUUID().toString());
        request.setInterfaceName(method.getDeclaringClass().getName());
        request.setServiceVersion(serviceVersion);
        request.setMethodName(method.getName());
        request.setParameterTypes(method.getParameterTypes());
        request.setParameters(args);
        // 获取 RPC 服务地址
        String serviceAddress = null;
        if (serviceDiscovery != null) {
            String serviceName = interfaceClass.getName();
            if (StringUtils.isNotEmpty(serviceVersion)) {
                serviceName += "-" + serviceVersion;
            }

            serviceAddress = serviceDiscovery.discover(serviceName);
            log.debug("discover service: {} => {}", serviceName, serviceAddress);
        }
        if (StringUtils.isEmpty(serviceAddress)) {
            throw new RuntimeException("server address is empty");
        }

        // 创建 RPC 客户端对象并发送 RPC 请求
        RpcClientHandler rpcClient = NettyRemoteManager.getInstance().chooseHandler(serviceAddress);
        long time = System.currentTimeMillis();
        RpcFuture rpcFuture = null;
        try {
            rpcFuture = rpcClient.sendRequest(request);
            log.debug("time: {}ms", System.currentTimeMillis() - time);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("get response error : %s", e.getMessage()));
        } finally {
            if (rpcFuture == null) {
                throw new RuntimeException("response is null");
            }
        }
        // 返回 RPC 响应结果
        return rpcFuture.get();
    }
}
