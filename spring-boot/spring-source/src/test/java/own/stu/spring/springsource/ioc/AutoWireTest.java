package own.stu.spring.springsource.ioc;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import own.stu.spring.springsource.config.ioc.BeanAutoWireConfig;
import own.stu.spring.springsource.less.controller.BookController;

public class AutoWireTest {

  @Test
  public void test_autowire() {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
        BeanAutoWireConfig.class);

    BookController bookController = annotationConfigApplicationContext.getBean(BookController.class);
    System.out.println(bookController.getBookService());

//    BookService bookService = annotationConfigApplicationContext.getBean(BookService.class);
//    System.out.println(bookService);
  }
}