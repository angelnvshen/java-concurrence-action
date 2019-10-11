package own.spring.core.springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import own.spring.core.springmvc.config.intereptor.ExceptionInterceptor;
import own.spring.core.springmvc.config.intereptor.LogInterceptor;

@Configuration
@ComponentScan("own.spring.core.springmvc.controller")
public class Config implements WebMvcConfigurer {

    @Bean
    public LogInterceptor logInterceptor(){
        return new LogInterceptor();
    }

    @Bean
    public ExceptionInterceptor exceptionInterceptor(){
        return new ExceptionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(exceptionInterceptor());
        registry.addInterceptor(logInterceptor());
    }
}
