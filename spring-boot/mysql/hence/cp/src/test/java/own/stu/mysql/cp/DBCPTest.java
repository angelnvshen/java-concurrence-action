package own.stu.mysql.cp;

import org.apache.commons.dbcp2.BasicDataSource;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import own.stu.mysql.cp.config.MyDBCPDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class DBCPTest {


    //引入 ContiPerf 进行性能测试
    @Rule
    public ContiPerfRule rule = new ContiPerfRule();


    int times = 2000;
    AtomicInteger time = new AtomicInteger(2001);

    //2个线程 共执行4次
    @PerfTest(invocations = 4, threads = 2)
    @Test
    public void testContiperf() {
        System.out.println(time.getAndIncrement());
    }

    //Throughput:	46 / s
    @Test
    @PerfTest(invocations = 2000, threads = 1)
    public void testCommonConn() throws SQLException {

        Connection conn = getConnection();
        Statement stat = conn.createStatement();
//        for (int i = 0; i < times; i++) {
        String sql = "insert into dbcp (name) values (" + time.getAndIncrement() + ")";
        stat.executeUpdate(sql);
//        }
        conn.close();
    }

    BasicDataSource dataSource = MyDBCPDataSource.dataSource;

    //Throughput:	284 / s
    @Test
    @PerfTest(invocations = 2000, threads = 1)
    public void testConnByDbcp() throws SQLException {

        Connection conn = dataSource.getConnection();
        Statement stat = conn.createStatement();
        String sql = "insert into dbcp (name) values (" + time.getAndIncrement() + ")";
        stat.executeUpdate(sql);
        stat.close();
        conn.close();
    }

    //获取一个数据库连接
    public static Connection getConnection() {

        try {
            Class.forName(connProperties.getProperty("driverClassName"));
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return DriverManager.getConnection(connProperties.getProperty("url"), connProperties.getProperty("username"), connProperties.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static Properties connProperties = getConnProperties();

    private static Properties getConnProperties() {
        Properties properties = new Properties();
        try {
            properties.load(MyDBCPDataSource.class.getResourceAsStream("/config/dbcp.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
