package own.stu.mysql.masterSlave.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@MapperScans({
        @MapperScan(
                basePackages = "own.stu.mysql.masterSlave.test.mapper",
                sqlSessionFactoryRef = "sqlSessionFactoryTest",
                sqlSessionTemplateRef = "sqlSessionTemplateTest"
        ),
        @MapperScan(
                basePackages = "own.stu.mysql.masterSlave.customer.mapper",
                sqlSessionFactoryRef = "sqlSessionFactoryCustomer",
                sqlSessionTemplateRef = "sqlSessionTemplateCustomer")
})
@Configuration
public class BeanConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.druid.test")
    public DataSource dataSourceTest() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.customer")
    public DataSource dataSourceCustomer() {
        return DruidDataSourceBuilder.create().build();
    }

    // ================= SqlSessionFactory =================
    //               == testSqlSessionFactory ==
    @Bean
    SqlSessionFactory sqlSessionFactoryTest() {
        SqlSessionFactory sessionFactory = null;
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSourceTest());
            sessionFactory = bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplateTest() {
        return new SqlSessionTemplate(sqlSessionFactoryTest());
    }

    //        === customerSqlSessionFactory ===
    @Bean
    SqlSessionFactory sqlSessionFactoryCustomer() {
        SqlSessionFactory sessionFactory = null;
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSourceCustomer());
            sessionFactory = bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplateCustomer() {
        return new SqlSessionTemplate(sqlSessionFactoryCustomer());
    }

    // =================== transactionManager===============

    @Bean
    PlatformTransactionManager testTransactionManager() {
        return new DataSourceTransactionManager(dataSourceTest());
    }

    @Bean
    public PlatformTransactionManager customerTransactionManager(@Qualifier("dataSourceCustomer") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
