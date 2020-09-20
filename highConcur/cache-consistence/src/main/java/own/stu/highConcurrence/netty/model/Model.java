package own.stu.highConcurrence.netty.model;

import lombok.Data;
import org.msgpack.annotation.Message;

import java.io.Serializable;

@Message
@Data
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;

    //类型
    private int type;

    //内容
    private String body;
}