package own.stu.mysql.pool;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class MyObject {

    static AtomicInteger id = new AtomicInteger(1);

    private String name;

    // true 表明对象可用,false表明当前对象不可用
    private boolean isValidate;

    private String state;

    private boolean passivateFlag;

    public MyObject(String name, boolean isValidate, String state) {
        this.name = name + "->" + id.getAndIncrement();
        this.isValidate = isValidate;
        this.state = state;
    }

    public void sayHello() {
        System.out.println(name + " say hello world");
    }
}