package own.stu.highConcurrence.cacheconsistence.nettyServer.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TranslatorData implements Serializable {

    private String id;
    private String name;
    private String message;    //传输消息体内容
}
