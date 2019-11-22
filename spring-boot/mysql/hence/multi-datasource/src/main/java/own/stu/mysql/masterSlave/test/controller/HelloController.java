package own.stu.mysql.masterSlave.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.mysql.masterSlave.test.mapper.UserMapper;
import own.stu.mysql.masterSlave.test.model.User;

import java.util.List;

@RestController
@RequestMapping("hello")
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users = userMapper.getAll();
        return users;
    }

    @RequestMapping("/getUser")
    public User getUser(Long id) {
        User user = userMapper.getOne(id);
        return user;
    }

    @Transactional
    @RequestMapping("/add")
    public void save(User user) {
        userMapper.insert(user);
//         int i = 10/0;
    }

    @RequestMapping(value = "update")
    public void update(User user) {
        userMapper.update(user);
    }

    @RequestMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userMapper.delete(id);
    }

}
