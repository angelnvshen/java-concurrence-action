package own.stu.shardingjdbc.shardingjdbc.simple.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import own.stu.shardingjdbc.shardingjdbc.simple.ShardingjdbcSimpleApplication;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShardingjdbcSimpleApplication.class})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testInsertUser() {
        Random random = new Random(10);
        for (int i = 20; i < 22; i++)
            userDao.insert("张三" + i, "1");
    }

    @Test
    public void testSelectUser() {
        List<Map> result = userDao.selectByUserIds(
                Arrays.asList(
                        1L,
                        19L,
                        2L));
        System.out.println(result);
    }
}
