package own.stu.mysql.pool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

public class MyObjectPool {

    private GenericObjectPool<MyObject> pool;

    public MyObjectPool(PoolableObjectFactory<MyObject> factory, MyObjectConfig config) {
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

    public static class MyObjectConfig extends GenericObjectPool.Config {

        public MyObjectConfig() {
            setTestWhileIdle(true);
            setMinEvictableIdleTimeMillis(60000L);
            setTimeBetweenEvictionRunsMillis(30000L);
            setNumTestsPerEvictionRun(-1);
        }

        public int getMaxIdle() {
            return maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxActive() {
            return maxActive;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }

        public long getMaxWait() {
            return maxWait;
        }

        public void setMaxWait(long maxWait) {
            this.maxWait = maxWait;
        }

        public byte getWhenExhaustedAction() {
            return whenExhaustedAction;
        }

        public void setWhenExhaustedAction(byte whenExhaustedAction) {
            this.whenExhaustedAction = whenExhaustedAction;
        }

        public boolean isTestOnBorrow() {
            return testOnBorrow;
        }

        public void setTestOnBorrow(boolean testOnBorrow) {
            this.testOnBorrow = testOnBorrow;
        }

        public boolean isTestOnReturn() {
            return testOnReturn;
        }

        public void setTestOnReturn(boolean testOnReturn) {
            this.testOnReturn = testOnReturn;
        }

        public boolean isTestWhileIdle() {
            return testWhileIdle;
        }

        public void setTestWhileIdle(boolean testWhileIdle) {
            this.testWhileIdle = testWhileIdle;
        }

        public long getTimeBetweenEvictionRunsMillis() {
            return timeBetweenEvictionRunsMillis;
        }

        public void setTimeBetweenEvictionRunsMillis(
                long timeBetweenEvictionRunsMillis) {
            this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        }

        public int getNumTestsPerEvictionRun() {
            return numTestsPerEvictionRun;
        }

        public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
            this.numTestsPerEvictionRun = numTestsPerEvictionRun;
        }

        public long getMinEvictableIdleTimeMillis() {
            return minEvictableIdleTimeMillis;
        }

        public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
            this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
        }

        public long getSoftMinEvictableIdleTimeMillis() {
            return softMinEvictableIdleTimeMillis;
        }

        public void setSoftMinEvictableIdleTimeMillis(
                long softMinEvictableIdleTimeMillis) {
            this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
        }
    }
}
