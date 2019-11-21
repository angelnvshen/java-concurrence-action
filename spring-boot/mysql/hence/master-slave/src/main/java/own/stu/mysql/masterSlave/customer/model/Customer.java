package own.stu.mysql.masterSlave.customer.model;

import lombok.Data;

@Data
public class Customer {

    private Long id;
    private String name;
    private Integer age;

    public Customer() {
        super();
    }

    public Customer(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
