package own.stu.highConcurrence.cacheconsistence.nettyServer.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestData<T> implements Serializable {
    T data;
}
