package own.stu.spring.springsource.less.service;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class BookService {

  private String label = "1";

  public BookService(String label) {
    this.label = label;
  }

  public BookService() {
  }
}
