package own.stu.mysql.masterSlave.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.mysql.masterSlave.customer.mapper.CustomerMapper;
import own.stu.mysql.masterSlave.customer.model.Customer;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerMapper customerMapper;

    @RequestMapping("/getCustomers")
    public List<Customer> getCustomers() {
        List<Customer> customers = customerMapper.getAll();
        return customers;
    }

    @RequestMapping("/getCustomer")
    public Customer getUser(Long id) {
        Customer customer = customerMapper.getOne(id);
        return customer;
    }

    @RequestMapping("/add")
    public void save(Customer customer) {
        customerMapper.insert(customer);
    }

}
