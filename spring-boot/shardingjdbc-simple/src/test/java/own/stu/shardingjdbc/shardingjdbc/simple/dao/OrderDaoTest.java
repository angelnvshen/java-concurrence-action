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
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void testInsertOrder() {
        Random random = new Random(10);
        for (int i = 0; i < 10; i++)
            orderDao.insert(new BigDecimal(90), (long) random.nextInt(10) + 1, 1);
    }

    @Test
    public void testSelectOrder() {
        List<Map> result = orderDao.selectByOrderIds(
                Arrays.asList(
//                        455693530111672320L,
                        455758704180985857L,
                        455758704180985897L));
        System.out.println(result);

    }

    @Test
    public void testSelectOrder_2() {
        List<Map> result = orderDao.selectByOrderIdAndUserId(
                Arrays.asList(
//                        455693530111672320L,
                        455767617840349185L,
                        455758704180985897L), 1L);
        System.out.println(result);

    }
}
