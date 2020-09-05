package own.stu.netty.rpcsim.common.loadBalance.impl;

import org.springframework.util.CollectionUtils;
import own.stu.netty.rpcsim.common.loadBalance.RpcLoadBalance;

import java.util.List;

public abstract class AbstractRpcLoadBalance<T> implements RpcLoadBalance<T> {

    @Override
    public T route(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return route0(list);
    }

    protected abstract T route0(List<T> list);
}
