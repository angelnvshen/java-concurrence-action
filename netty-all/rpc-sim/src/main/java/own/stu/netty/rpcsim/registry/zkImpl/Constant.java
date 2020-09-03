package own.stu.netty.rpcsim.registry.zkImpl;

/**
 * 常量
 */
public interface Constant {

    int ZK_SESSION_TIMEOUT = 5000;
    int ZK_CONNECTION_TIMEOUT = 1000;

    String ZK_REGISTRY_PATH = "/registry";

    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";

    String ZK_NAMESPACE = "netty-rpc";

    String ADDRESS = "192.168.0.128:2181";
}