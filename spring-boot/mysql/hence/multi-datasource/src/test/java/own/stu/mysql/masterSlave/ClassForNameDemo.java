package own.stu.mysql.masterSlave;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

public class ClassForNameDemo {

    private void print() {
        System.out.println(" ======= ");
    }

    @Test
    public void testJDBCConnect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Properties properties = getProperties();
        Connection connection = DriverManager.getConnection(
                (String)properties.get("url"),
                (String)properties.get("username"),
                (String)properties.get("password"));
        littleQuery(connection);
    }

    private void littleQuery(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select id from users limit 1");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getInt("id"));
        }
    }

    @Test
    public void test() throws Exception {

        /*Class<?> aClass = getClass().getClassLoader().loadClass("own.stu.mysql.masterSlave.ClassForNameDemo");
        ClassForNameDemo classForNameDemo = (ClassForNameDemo) aClass.newInstance();
        classForNameDemo.print();*/

        /*Class<?> aClass = Class.forName("own.stu.mysql.masterSlave.ClassForNameDemo");
        System.out.println(aClass.getClassLoader());
        ClassForNameDemo classForNameDemo = (ClassForNameDemo) aClass.newInstance();
        classForNameDemo.print();*/

        /*
         */

        Properties properties = getProperties();

        java.sql.Driver driver = new Driver();
//        Connection connection = driver.connect("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true", properties);
        Connection connection = DriverManager.getConnection(
                (String)properties.get("url"),
                (String)properties.get("username"),
                (String)properties.get("password"));
        littleQuery(connection);

    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.putIfAbsent("url", "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true");
        properties.putIfAbsent("username", "root");
        properties.putIfAbsent("user", "root");
        properties.putIfAbsent("password", "123456");
        return properties;
    }
}
