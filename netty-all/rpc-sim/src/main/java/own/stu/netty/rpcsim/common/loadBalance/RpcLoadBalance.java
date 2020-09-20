package own.stu.netty.rpcsim.common.loadBalance;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by luxiaoxun on 2020-08-01.
 */
public interface RpcLoadBalance<T> {

    // Route the connection for service key
    T route(List<T> list);

    default T getFirst(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }
}
