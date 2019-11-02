package own.stu.redis.oneMaster.fakeDistribute.service.inner;

public interface DistributedServer {

    String getServer();

    String dealBody(String body);
}
