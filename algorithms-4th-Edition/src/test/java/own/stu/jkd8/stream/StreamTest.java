package own.stu.jkd8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import own.stu.jkd8.model.Employee;
import own.stu.jkd8.model.Trader;
import own.stu.jkd8.model.Transaction;

public class StreamTest {

  // 创建stream流
  @Test
  public void test1() {

    List<String> list = new ArrayList<>();
    list.stream();

    Arrays.stream(new Employee[]{});

    Stream<String> stream = Stream.of("aa", "bb");

    Stream.iterate(0, t -> t + 2)
        .limit(10)
        .forEach(System.out::println);

    System.out.println(" ====== ");

    Stream.generate(Math::random)
        .limit(10)
        .forEach(System.out::println);
  }

  // 操作
  @Test
  public void test2() {
    // filter, limit, skip, distinct, foreach
  }

  // 映射
  @Test
  public void test3() {
    // map, flatMap
    List<String> list = Arrays.asList("aa", "vv", "fe", "aa", "we", "io", "w3");
    list.stream()
        .map((str) -> str.toUpperCase())
        .distinct()
        .limit(5)
        .forEach(System.out::println);
  }

  // 排序
  @Test
  public void test4() {
    // sorted, sorted(Comparator com)
  }

  // 查找和匹配
  @Test
  public void test5() {
    //
  }

  // 归约
  @Test
  public void test6() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
    Integer sum = list.stream()
        .reduce(0, (x, y) -> x + y);
    System.out.println(sum);
  }

  // 终止操作
  @Test
  public void test7() {
    //
  }

  //test  1,2,3,4,5 -> 1,4,9,16,25
  @Test
  public void test8() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    List<Double> result = list.stream()
        .map((x) -> Math.pow(x, 2))
        .collect(Collectors.toList());
    System.out.println(result);

  }

  List<Employee> employeeList = Arrays.asList(
      new Employee("QIN", 10, 323.22),
      new Employee("OPQ", 20, 2333),
      new Employee("NIN", 13, 533),
      new Employee("WUV", 19, 432),
      new Employee("KMN", 19, 432)
  );

  @Test
  public void test9() {

    Optional<Integer> result = employeeList.stream()
        .map((x) -> 1)
        .reduce(Integer::sum);

    System.out.println(result.get());
  }

  @Test
  public void test10(){

    Trader ronaldo = new Trader("Ronaldo", "SH");
    Trader mario = new Trader("Mario", "BJ");
    Trader adole = new Trader("Adole", "XH");
    Trader beyoncé = new Trader("Beyoncé", "XH");

    List<Transaction> list = Arrays.asList(
        new Transaction(beyoncé, 2011, 2000),
        new Transaction(ronaldo, 2012, 1000),
        new Transaction(ronaldo, 2011, 4000),
        new Transaction(mario, 2013, 7000),
        new Transaction(mario, 2011, 1000),
        new Transaction(adole, 2014, 6000)
    );

    List<Transaction> result =
    list.stream()
        .filter((t) -> t.getYear() == 2011)
        .sorted(Comparator.comparingInt(Transaction::getValue))
        .collect(Collectors.toList());
    System.out.println(result);

    List<String> cityList =
    list.stream()
        .map((t) -> t.getTrader().getCity())
        .distinct()
        .collect(Collectors.toList());
    System.out.println(cityList);

    List<Trader> traderList =
    list.stream()
        .map((t) -> t.getTrader())
        .collect(Collectors.toList())
        .stream()
        .filter((t) -> t.getCity().equals("XH"))
        .sorted(Comparator.comparing(Trader::getName))
        .collect(Collectors.toList());

    System.out.println(traderList);

    List<String> nameList =
    list.stream()
        .map((t) -> t.getTrader().getName())
        .collect(Collectors.toList())
        .stream()
        .distinct()
        .sorted(Comparator.comparing(t->t))
        .collect(Collectors.toList());

    System.out.println(nameList);

    Boolean booleanResult =
    list.stream()
        .anyMatch((t) -> t.getTrader().getCity().equals("BJ"));

    System.out.println(booleanResult);

    list.stream()
        .filter((t) -> t.getTrader().getCity().equals("XH"))
        .forEach((t) -> System.out.println(t.getValue()));

    Optional<Integer> maxValue =
    list.stream()
        .map((t) -> t.getValue())
        .max(Integer::compareTo);
    System.out.println(maxValue.get());

    Optional<Transaction> min =
    list.stream()
        .min(Comparator.comparingInt(Transaction::getValue));
    System.out.println(min.get());
  }
}
