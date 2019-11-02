package own.stu.redis.oneMaster.fakeDistribute.service.inner;

import org.springframework.util.Assert;

public class W2wzServerImpl implements DistributedServer {

    static String code = "W2WZ";

    @Override
    public String getServer() {
        return "http://1.w2wz.com/upload.php";
    }

    @Override
    public String dealBody(String body) {
        Assert.notNull(body, "body is null");
        String xx = "URL: (<a href=\"";
        int index = body.indexOf("URL: (<a href=\"");
        String substring = body.substring(index + xx.length());
        int index1 = substring.indexOf("\"");
        return substring.substring(0, index1);
    }
}
