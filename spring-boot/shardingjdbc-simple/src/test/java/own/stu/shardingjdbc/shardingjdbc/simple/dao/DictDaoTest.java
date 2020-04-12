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
public class DictDaoTest {

    @Autowired
    private DictDao dictDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void testInsertDict() {
        dictDao.insert("user_type", "0", "系统管理员");
        dictDao.insert("user_type", "1", "用户");
    }

    @Test
    public void testSelectDict() {
        List<Map> result = dictDao.selectByType("user_type");
        System.out.println(result);
    }

    @Test
    public void testDeleteDict() {
        int result = dictDao.deleteByIdList(
                Arrays.asList(
                        1L,
                        2L));
        System.out.println(result);
    }

    @Test
    public void testJoinUserInfo(){
        List<Map> maps = userDao.selectByUserIds_2(Arrays.asList(1L, 2L, 3L));
        System.out.println(maps);
    }
}
