package own.spring.core.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyOverrideConfigurer;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@Configuration
public class BeanFactoryPostProcessorConfig {

  @Value("${jdbc.url}")
  private String jdbc_url;

  @Bean
  public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
    PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
    configurer.setLocation(new ClassPathResource("jdbc.properties"));
    return configurer;
  }

//  @Bean
  public static PropertyOverrideConfigurer propertyOverrideConfigurer() {
    PropertyOverrideConfigurer configurer = new PropertyOverrideConfigurer();
    configurer.setLocation(new ClassPathResource("jdbc_hence.properties"));
    return configurer;
  }

  @Bean("dataSource")
  public BasicDataSource getBasicDataSource() {

    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(jdbc_url);
    basicDataSource.setDriverClassName("${jdbc.driver}");
    basicDataSource.setUsername("${jdbc.username}");
    basicDataSource.setPassword("${jdbc.password}");
    basicDataSource.setMaxTotal(10);
    return basicDataSource;
  }
}
