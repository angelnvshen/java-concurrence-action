package own.stu.mysql.cp;

import org.junit.jupiter.api.Test;
import own.stu.mysql.pool.MyObject;
import own.stu.mysql.pool.MyObjectPool;
import own.stu.mysql.pool.MyPoolableObjectFactory;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyObjectPoolTest {

    @Test
    public void test() {
        //创建对象池
        MyObjectPool pool = new MyObjectPool(new MyPoolableObjectFactory(), getConfig());
        //从对象池中借出一个对象
        MyObject object = pool.borrowObject();
        object.sayHello();
        //object.setValidate(false);
        //归还对象
        System.out.println("***归还对象***");
        pool.returnObject(object);
    }

    @Test
    public void testMulti() throws InterruptedException {

        MyObjectPool pool = new MyObjectPool(new MyPoolableObjectFactory(), getConfig());

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Random random = new Random();

        for (int i = 0; i < 10; i++)
            executorService.submit(() -> {
                MyObject myObject = pool.borrowObject();
                myObject.sayHello();

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //归还对象
                System.out.println("***归还对象***" + myObject);

                if (random.nextBoolean()) {
                    pool.invalidateObject(myObject);
                }
                pool.returnObject(myObject);

            });

        TimeUnit.SECONDS.sleep(30);
    }

    @Test
    public void testCycle() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextBoolean());
        }
    }

    /**
     * 配置对象池的相关参数
     *
     * @return
     */
    public static MyObjectPool.MyObjectConfig getConfig() {
        MyObjectPool.MyObjectConfig config = new MyObjectPool.MyObjectConfig();
        config.setMaxTotal(5);
        config.setMaxIdle(3);
        config.setMinIdle(0);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        return config;
    }
}
