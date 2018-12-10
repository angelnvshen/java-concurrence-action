package own.stu.tkmybatis.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import own.stu.com.plugin.demo.config.EnableStatisticsTimeConfig;

@Import(EnableStatisticsTimeConfig.class)
@Configuration
public class StatisticsTimeConfig {

}
