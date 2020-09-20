package own.stu.highConcurrence.cacheconsistence.nettyServer.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private Long id;

    private String key;

    private Status status;

    public Result() {

    }

    public Result(String key) {
        this.key = key;
    }

    public Result(Long id, String key, Status status) {
        this.id = id;
        this.key = key;
        this.status = status;
    }

    public enum Status {
        SUCCESS,
        EXCEPTION
    }
}