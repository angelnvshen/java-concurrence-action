package own.stu.mysql.masterSlave.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    public static String TEST = "test";
    public static String CUSTOMER = "customer";

    @Override
    protected Object determineCurrentLookupKey() {
        return DBUtils.getDB();
    }

    public static class DBUtils {
        static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> TEST);

        public static void setDB(String dbFlag) {
            threadLocal.set(dbFlag);
        }

        public static void setDefaultDB() {
            setDB(TEST);
        }

        public static String getDB() {
            return threadLocal.get();
        }
    }
}
