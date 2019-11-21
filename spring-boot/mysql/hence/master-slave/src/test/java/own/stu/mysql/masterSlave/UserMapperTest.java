package own.stu.mysql.masterSlave;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import own.stu.mysql.masterSlave.mapper.UserMapper;
import own.stu.mysql.masterSlave.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() throws Exception {
        userMapper.insert(new User("aa1", "a123456", User.UserSexEnum.MAN));
        userMapper.insert(new User("bb1", "b123456", User.UserSexEnum.WOMAN));
        userMapper.insert(new User("cc1", "b123456", User.UserSexEnum.WOMAN));

        Assert.assertEquals(3, userMapper.getAll().size());
    }
}