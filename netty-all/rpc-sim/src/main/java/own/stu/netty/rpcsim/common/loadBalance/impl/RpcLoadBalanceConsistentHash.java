//package own.stu.netty.rpcsim.common.loadBalance.impl;
//
//import own.stu.netty.rpcsim.client.handler.RpcClientHandler;
//import own.stu.netty.rpcsim.common.loadBalance.RpcLoadBalance;
//
//public class RpcLoadBalanceConsistentHash<T> extends AbstractRpcLoadBalance<T> implements RpcLoadBalance<T> {
//
//    public RpcProtocol doRoute(String serviceKey, List<RpcProtocol> addressList) {
//        int index = Hashing.consistentHash(serviceKey.hashCode(), addressList.size());
//        return addressList.get(index);
//    }
//
//    @Override
//    public RpcProtocol route(String serviceKey, Map<RpcProtocol, RpcClientHandler> connectedServerNodes) throws Exception {
//        Map<String, List<RpcProtocol>> serviceMap = getServiceMap(connectedServerNodes);
//        List<RpcProtocol> addressList = serviceMap.get(serviceKey);
//        if (addressList != null && addressList.size() > 0) {
//            return doRoute(serviceKey, addressList);
//        } else {
//            throw new Exception("Can not find connection for service: " + serviceKey);
//        }
//    }
//}
