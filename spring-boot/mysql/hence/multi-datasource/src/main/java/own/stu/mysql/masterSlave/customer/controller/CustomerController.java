package own.stu.mysql.masterSlave.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional(value = "customerTransactionManager")
    @RequestMapping("/add")
    public void save(Customer customer) {
        customerMapper.insert(customer);
//        int i = 10/0;
    }

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/context")
    public void context() {
        DataSourceTransactionManager applicationContextBean = applicationContext.getBean(DataSourceTransactionManager.class);
        System.out.println(applicationContextBean);
    }

}
