package own.stu.netty.rpcsim.common.loadBalance.impl;

import own.stu.netty.rpcsim.common.loadBalance.RpcLoadBalance;

import java.util.List;
import java.util.Random;

/**
 * Random load balance
 */
public class RpcLoadBalanceRandom<T> extends AbstractRpcLoadBalance<T> implements RpcLoadBalance<T> {
    private Random random = new Random();

    @Override
    protected T route0(List<T> list) {
        int size = list.size();
        // Random
        return list.get(random.nextInt(size));
    }
}
