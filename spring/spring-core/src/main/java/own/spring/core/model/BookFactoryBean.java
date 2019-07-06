package own.spring.core.model;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import own.spring.core.model.Book;

@Component
public class BookFactoryBean implements FactoryBean<Book> {

  @Override
  public Book getObject() throws Exception {
    return new Book();
  }

  @Override
  public Class<?> getObjectType() {
    return Book.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }
}
