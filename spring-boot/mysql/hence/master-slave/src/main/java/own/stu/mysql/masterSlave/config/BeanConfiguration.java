package own.stu.mysql.masterSlave.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

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
    public DataSource dataSourceDbcp() {
        return DruidDataSourceBuilder.create().build();
    }

}
