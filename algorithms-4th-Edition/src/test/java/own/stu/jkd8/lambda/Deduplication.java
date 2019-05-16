package own.stu.jkd8.lambda;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import lombok.Data;

public class Deduplication {

  public static void main(String[] args) {

    List<Book> books = Arrays.asList(
        new Book(12, "Sun Java"),
        new Book(12, "Oracle Java"),
        new Book(15, "Scala"),
        new Book(17, "Scala")
    );

    List<Book> unique = books.stream().collect(
        collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.name))),
            ArrayList::new));

    unique.forEach(book -> System.out.printf("book[id: %s, name: %s]\n", book.id, book.name));
  }
}

@Data
class Book {

  public final Integer id;
  public final String name;

  public Book(Integer id, String name) {
    this.id = id;
    this.name = name;
  }
}
