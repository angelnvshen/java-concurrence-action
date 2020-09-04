package own.stu.netty.rpcsim.client.bean;

public interface AsyncRPCCallback {

    void success(Object result);

    void fail(Exception e);
}
