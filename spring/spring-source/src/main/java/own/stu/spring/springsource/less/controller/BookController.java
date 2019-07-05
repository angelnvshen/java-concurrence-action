package own.stu.spring.springsource.less.controller;

import javax.annotation.Resource;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import own.stu.spring.springsource.less.service.BookService;

@Controller
public class BookController {

//  @Qualifier("bookService")
//  @Autowired

  @Resource(name = "bookService")

//  @Qualifier("bookService2")
//  @Inject
  private BookService bookService;

  public BookService getBookService() {
    return bookService;
  }
}
