package own.stu.mysql.cp.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MyDBCPDataSource {
    public static BasicDataSource dataSource = getDBCPDataSource();

    private MyDBCPDataSource() {
    }

    private static BasicDataSource getDBCPDataSource() {
        // 加载配置文件
        Properties properties = new Properties();
        try {
            properties.load(MyDBCPDataSource.class.getResourceAsStream("/config/dbcp.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获取配置
        if (properties.isEmpty()) {
            return null;
        }
        BasicDataSource dataSource = new BasicDataSource();
        // 基础配置
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setDriverClassName(properties.getProperty("driverClassName"));

        // 数据库连接配置，主要由数据库驱动提供
        dataSource.setDefaultAutoCommit(true);
        // ...
        // 连接池的相关配置，这部分的默认配置完全由apache-commons-pool组件提供
        dataSource.setInitialSize(GenericObjectPool.DEFAULT_MIN_IDLE);
        dataSource.setMaxActive(GenericObjectPool.DEFAULT_MAX_ACTIVE);
        dataSource.setMaxIdle(GenericObjectPool.DEFAULT_MAX_ACTIVE);
        dataSource.setMinIdle(GenericObjectPool.DEFAULT_MIN_IDLE);
        dataSource.setMaxWait(GenericObjectPool.DEFAULT_MAX_WAIT);
        dataSource.setTestOnBorrow(GenericObjectPool.DEFAULT_TEST_ON_BORROW);
        dataSource.setTestOnReturn(GenericObjectPool.DEFAULT_TEST_ON_RETURN);
        dataSource.setTestWhileIdle(GenericObjectPool.DEFAULT_TEST_WHILE_IDLE);
        // 其他配置见 http://commons.apache.org/proper/commons-dbcp/configuration.html
        return dataSource;
    }

    public static void main(String[] args) {
        BasicDataSource dataSource = MyDBCPDataSource.getDBCPDataSource();
        System.out.println("当前数据库连接池的容量" + dataSource.getNumActive());

        Statement sm = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            sm = connection.createStatement();
            rs = sm.executeQuery("select id From users");
            while (rs.next()) {
                long id = rs.getLong(1);
                System.out.println("从数据库中得到一条记录的值" + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                sm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("当前数据库连接池的容量" + dataSource.getNumActive());
                connection.close();
                System.out.println("当前数据库连接池的容量" + dataSource.getNumActive());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
