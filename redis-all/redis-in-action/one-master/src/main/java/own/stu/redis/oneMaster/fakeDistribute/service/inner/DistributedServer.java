package own.stu.redis.oneMaster.fakeDistribute.service.inner;

import org.springframework.http.HttpEntity;
import org.springframework.util.MultiValueMap;

import java.io.File;

public interface DistributedServer {

    // 上传文件的服务器地址
    String getServer();

    // 应该保证上传成功后处理
    String dealBodyAfterSendFilePart(String body);

    HttpEntity<MultiValueMap<String, Object>> getHttpEntity(File file);

    // TODO 可以ping 服务器上传地址是否开启，ignore and filter it
}
