package own.stu.netty.rpcsim.common.bean;

import lombok.Data;

@Data
public class RpcResponse<T> {

    private String requestId;

    private Exception exception;

    private T result;

    public boolean hasException() {
        return exception != null;
    }
}
