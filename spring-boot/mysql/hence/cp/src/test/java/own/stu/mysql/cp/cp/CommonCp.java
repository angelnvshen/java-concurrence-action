package own.stu.mysql.cp.cp;

import com.alibaba.druid.pool.DruidPooledConnection;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import own.stu.mysql.cp.config.MyDBCPDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class CommonCp {

    //引入 ContiPerf 进行性能测试
    @Rule
    public ContiPerfRule rule = new ContiPerfRule();

    public static Properties getConnProperties(String path) {
        Properties properties = new Properties();
        try {
            properties.load(MyDBCPDataSource.class.getResourceAsStream("/config/"+path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    AtomicInteger time = new AtomicInteger(2001);

    //2个线程 共执行4次
    @PerfTest(invocations = 4, threads = 2)
    @Test
    public void testContiperf() {
        System.out.println(time.getAndIncrement());
    }

    public void oneInertConnect(Connection connection) throws SQLException {

        Statement stat = connection.createStatement();
        String sql = "insert into dbcp (name) values (" + time.getAndIncrement() + ")";
        stat.executeUpdate(sql);
        stat.close();
        connection.close();
    }
}
