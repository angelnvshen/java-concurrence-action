package own.stu.mysql.cp.cp;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.databene.contiperf.PerfTest;
import org.junit.Test;

import java.sql.Connection;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class HikariTest extends CommonCp{

    HikariDataSource ds = null;
    Properties druidProperties = getConnProperties("hikari.properties");
    {
        HikariConfig config = new HikariConfig(druidProperties);
        ds = new HikariDataSource(config);
    }

    @Test
    public void test(){
        System.out.println(ds.getMaximumPoolSize());
    }

    AtomicInteger time = new AtomicInteger(6001);

    // Throughput:	325 / s
    @PerfTest(invocations = 2000, threads = 1)
    @Test
    public void testConn() throws Exception{
        Connection connection = ds.getConnection();
        oneInertConnect(connection);
    }
}
