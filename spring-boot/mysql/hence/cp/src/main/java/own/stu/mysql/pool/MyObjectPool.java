package own.stu.mysql.pool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class MyObjectPool {

    private GenericObjectPool<MyObject> pool;

    public MyObjectPool(PooledObjectFactory<MyObject> factory, MyObjectConfig config) {
        pool = new GenericObjectPool<>(factory, config);
    }

    /**
     * 从对象池中返回一个对象
     *
     * @return
     */
    public MyObject borrowObject() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将已经使用过的对象返还到对象池
     *
     * @param obj
     */
    public void returnObject(MyObject obj) {
        try {
            pool.returnObject(obj);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /**
     * 使对象池中的obj对象无效
     *
     * @param obj
     */
    public void invalidateObject(MyObject obj) {
        try {
            //内部调用了池对象工厂的destroyObject方法
            pool.invalidateObject(obj);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /**
     * 关闭连接池
     */
    public void destory() {
        try {
            pool.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static class MyObjectConfig extends GenericObjectPoolConfig {

        public MyObjectConfig() {
            setTestWhileIdle(true);
            setMinEvictableIdleTimeMillis(60000L);
            setTimeBetweenEvictionRunsMillis(30000L);
            setNumTestsPerEvictionRun(-1);
        }
    }
}
