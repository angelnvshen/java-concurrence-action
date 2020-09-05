package own.stu.netty.rpcsim.common.loadBalance.impl;

import own.stu.netty.rpcsim.common.loadBalance.RpcLoadBalance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Round robin load balance
 */
public class RpcLoadBalanceRoundRobin<T> extends AbstractRpcLoadBalance<T> implements RpcLoadBalance<T> {

    private AtomicInteger roundRobin = new AtomicInteger(0);

    @Override
    protected Object route0(List list) {
        int size = list.size();
        // Round robin
        int index = (roundRobin.getAndAdd(1) + size) % size;
        return list.get(index);
    }
}
