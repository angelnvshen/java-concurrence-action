package own.spring.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;


@ComponentScans({
    @ComponentScan("own.spring.core.model.inject"),
})
@Configuration
public class InjectBeanConfig {

}
