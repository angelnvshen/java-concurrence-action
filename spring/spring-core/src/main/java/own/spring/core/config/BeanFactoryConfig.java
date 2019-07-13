package own.spring.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@ComponentScans({
//    @ComponentScan("own.spring.core.model.factory")
    @ComponentScan("own.spring.core.replace"),
    @ComponentScan("own.spring.core.reveal"),
    /*@ComponentScan(value = "own.spring.core.model",
            includeFilters = {
                    @ComponentScan.Filter(type = FilterType.REGEX, pattern = "own.spring.core.model.factory.*")
            })*/
})
@Configuration
public class BeanFactoryConfig {

}
