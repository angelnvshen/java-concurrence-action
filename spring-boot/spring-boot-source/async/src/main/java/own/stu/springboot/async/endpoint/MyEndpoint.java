package own.stu.springboot.async.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "my-endpoint")
public class MyEndpoint {

    @ReadOperation
    public Map<String, Object> endpoint() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("message", "this is my endpoint");
        return map;
    }
}