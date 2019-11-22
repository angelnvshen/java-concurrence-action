package own.stu.mysql.masterSlave.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@MapperScans({
        @MapperScan(
                basePackages = "own.stu.mysql.masterSlave.test.mapper"
        ),
        @MapperScan(
                basePackages = "own.stu.mysql.masterSlave.customer.mapper"
        )
})
@Configuration
public class BeanDynamicConfiguration {

    // @Primary
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

    @Bean
    public DataSource dynamicDataSource() {

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DynamicDataSource.TEST, dataSourceTest());
        dataSourceMap.put(DynamicDataSource.CUSTOMER, dataSourceCustomer());

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dataSourceMap);

        dynamicDataSource.setDefaultTargetDataSource(dataSourceTest());

        return dynamicDataSource;
    }

    @Bean
    SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dynamicDataSource());
            sessionFactory = bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate() {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    PlatformTransactionManager testTransactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
