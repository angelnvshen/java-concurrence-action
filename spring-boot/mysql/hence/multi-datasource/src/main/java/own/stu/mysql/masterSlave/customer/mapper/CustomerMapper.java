package own.stu.mysql.masterSlave.customer.mapper;

import org.apache.ibatis.annotations.*;
import own.stu.mysql.masterSlave.customer.model.Customer;

import java.util.List;

public interface CustomerMapper {

    @Select("SELECT * FROM customer")
    List<Customer> getAll();

    @Select("SELECT * FROM customer WHERE id = #{id}")
    Customer getOne(Long id);

    @Insert("INSERT INTO customer(name,age) VALUES(#{name}, #{age})")
    void insert(Customer user);
}
