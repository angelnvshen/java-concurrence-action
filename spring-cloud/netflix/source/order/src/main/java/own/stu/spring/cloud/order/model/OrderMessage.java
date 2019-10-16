package own.stu.spring.cloud.order.model;

import lombok.Data;
import org.springframework.util.Assert;

@Data
public class OrderMessage<T> {

    private Integer code;

    private String message;

    private T data;

    public OrderMessage(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public OrderMessage() {
    }

    public static <T> OrderMessage success(T t) {
        Assert.notNull(t, "结果数据为空");
        return new OrderMessage(200, "SUCCESS", t);
    }

    public static <T> OrderMessage fail() {
        return fail("FAIL");
    }

    public static <T> OrderMessage fail(String message) {
        Assert.notNull(message, "错误信息为空");
        return new OrderMessage(-100, message, null);
    }
}