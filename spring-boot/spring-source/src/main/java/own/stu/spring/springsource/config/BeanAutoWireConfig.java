package own.stu.spring.springsource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import own.stu.spring.springsource.less.service.BookService;

@ComponentScan("own.stu.spring.springsource.less")
@Configuration
public class BeanAutoWireConfig {

  @Primary
  @Bean("bookService2")
  public BookService bookService(){
    BookService bookService = new BookService();
    bookService.setLabel("2");
    return bookService;
  }
}