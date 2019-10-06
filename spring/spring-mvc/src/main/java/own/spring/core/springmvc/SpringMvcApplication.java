package own.spring.core.springmvc;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.expression.Lists;
import own.spring.core.springmvc.spring.boot.servlet.MyFilter2;
import own.spring.core.springmvc.spring.boot.servlet.MyServlet2;
import own.spring.core.springmvc.spring.boot.servlet.MyServletHttpRequestListener2;

@ServletComponentScan(value = "own.spring.core.springmvc.servlet")
@SpringBootApplication
public class SpringMvcApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringMvcApplication.class, args);
  }

  @Bean
  public ServletRegistrationBean servletRegistrationBean() {
    ServletRegistrationBean registrationBean = new ServletRegistrationBean();
    registrationBean.setServlet(new MyServlet2());
    registrationBean.setUrlMappings(Arrays.asList("/myServlet-2"));
    return registrationBean;
  }

  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new MyFilter2());
    filterRegistrationBean.addUrlPatterns("/myServlet-2");
    return filterRegistrationBean;
  }

  @Bean
  public ServletListenerRegistrationBean listenerRegistrationBean() {
    ServletListenerRegistrationBean listenerRegistrationBean = new ServletListenerRegistrationBean();
    listenerRegistrationBean.setListener(new MyServletHttpRequestListener2());
    return listenerRegistrationBean;
  }
}
