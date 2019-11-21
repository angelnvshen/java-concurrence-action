package own.stu.mysql.cp.cp;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.databene.contiperf.PerfTest;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class DruidTest extends CommonCp {

    DruidDataSource druidDataSource = null;

    {
        Properties druidProperties = getConnProperties("druid.properties");
        druidDataSource = new DruidDataSource();
        druidDataSource.setConnectProperties(druidProperties);
    }

    AtomicInteger time = new AtomicInteger(4011);

    // Throughput:	318 / s
    @PerfTest(invocations = 1990, threads = 1)
    @Test
    public void test() throws SQLException {
        DruidPooledConnection conn = druidDataSource.getConnection();
        Statement stat = conn.createStatement();
        String sql = "insert into dbcp (name) values (" + time.getAndIncrement() + ")";
        stat.executeUpdate(sql);
        stat.close();
        conn.close();
    }
}
