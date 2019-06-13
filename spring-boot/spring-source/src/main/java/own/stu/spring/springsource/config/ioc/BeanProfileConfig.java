package own.stu.spring.springsource.config.ioc;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringValueResolver;
import own.stu.spring.springsource.model.RainBow;

@Configuration
public class BeanProfileConfig implements EmbeddedValueResolverAware {

  @Value("${db.name}")
  private String userName;

  @Value("${db.password}")
  private String password;

  private String driver;

  private StringValueResolver stringValueResolver;

  @Profile("dev")
  @Bean("dev")
  public DruidDataSource dataSourceDev() throws Exception {
    DruidDataSource druidDataSource = new DruidDataSource();
    druidDataSource.setUsername(userName);
    druidDataSource.setPassword(password);
    druidDataSource.setDriverClassName(driver);
    druidDataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8");
    return druidDataSource;
  }

  @Profile("default")
  @Bean("test")
  public DruidDataSource dataSourceTest() throws Exception {
    DruidDataSource druidDataSource = new DruidDataSource();
    druidDataSource.setUsername(userName);
    druidDataSource.setPassword(password);
    druidDataSource.setDriverClassName(driver);
    druidDataSource.setUrl("jdbc:mysql://localhost:3306/sakila?useUnicode=true&characterEncoding=utf8");
    return druidDataSource;
  }

  @Bean
  public RainBow rainBow(){
    return new RainBow();
  }


  @Override
  public void setEmbeddedValueResolver(StringValueResolver resolver) {
    stringValueResolver = resolver;
    driver = resolver.resolveStringValue("${db.driver}");
  }
}
