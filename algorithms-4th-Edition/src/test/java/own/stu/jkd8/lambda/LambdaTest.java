package own.stu.jkd8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.junit.Test;
import own.stu.jkd8.lambda.service.MyOperate;
import own.stu.jkd8.lambda.service.StringOperate;
import own.stu.jkd8.model.Employee;

public class LambdaTest {

  // 类型推断
  @Test
  public void test(){

    String[] Str = {"11", "22", "33"};
    /*
    编译不通过，没有类型推断
    String[] Str2;
    Str2 = {"11", "22", "33"}; */

//    show(new HashMap<>()); // 1.7 编译不通过，没有类型推断
  }

  public void show(Map<String, String> info){

  }

  @Test
  public void test1(){
    Runnable runnable = ()-> System.out.println("hello");
    runnable.run();
  }

  @Test
  public void test2(){
    /*Comparator<String> comparable = (x, y) -> {
      System.out.println(x + ", " + y);
      return x.compareTo(y);
    };*/

    Comparator<String> comparable = (x, y) ->  x.compareTo(y);

    System.out.println(comparable.compare("s", "S"));
  }

  @Test
  public void test3() {

    System.out.println(getOperateValue(200, (num -> num * 2)));
    System.out.println(getOperateValue(200, (num -> num / 2)));
    System.out.println(getOperateValue(200, (num -> num / 0)));
  }

  public int getOperateValue(int num, MyOperate operate){
    return operate.operate(num);
  }

  List<Employee> employeeList = Arrays.asList(
      new Employee("QIN", 10, 323.22),
      new Employee("OPQ", 20, 2333),
      new Employee("NIN", 13, 533),
      new Employee("WUV", 19, 432),
      new Employee("KMN", 19, 432)
  );

  @Test
  public void test4() {
    System.out.println(employeeList);

    Collections.sort(employeeList, (x, y)->
      x.getAge() == y.getAge()? x.getName().compareTo(y.getName()) : x.getAge() - y.getAge());

    System.out.println(" ------ ");
    System.out.println(employeeList);

  }

  @Test
  public void test5() {

    System.out.println(StringUtil("hello world ", (x)->x.toUpperCase() ));

    StringOperate operate = (x)->{
      int index1 = 0, index2 = 0;
      if(x.length() > 2){
        index1 = 1;
      }
      if(x.length() > 4){
        index2 = 3;
      }

      return index1 == 0 ? x : (index2 == 0 ? x.substring(index1) : x.substring(index1, index2));
    };

    System.out.println(StringUtil("hi ", operate));
    System.out.println(StringUtil("-", operate));
    System.out.println(StringUtil("qin", operate));
    System.out.println(StringUtil("hello world ", operate));
  }

  public String StringUtil(String str, StringOperate operate){
    return operate.getValue(str);
  }

  /**
   * 四大内置核心lambda接口:
   *  Consumer  - void accept(T t)
   *  Supplier  - T get()
   *  Function  - R apply(T t)
   *  Predicate - boolean test(T t)
    */
  @Test
  public void test6() {
    Consumer consumer = (x) -> System.out.println("消费：" + x);
    consumer.accept(100);
    consumer.andThen(consumer).accept(200);
  }

  @Test
  public void test7() {
    Supplier<Integer> supplier = () -> (int)(Math.random()*100);
    for (int i = 0; i < 10; i++) {
      System.out.println(supplier.get());
    }
  }

  @Test
  public void test8() {
    System.out.println(strHandler("hwkoef", (x) -> x.toUpperCase()));
    System.out.println(strHandler("hwkoef", (x) -> x.concat("xxxx")));
  }

  private String strHandler(String str, Function<String, String> f){
    return f.apply(str);
  }

  @Test
  public void test9() {
    Predicate<String> predicate = (x) -> x.length() > 3;
    List<String> lst = Arrays.asList("wefwf", "ssdf", "xx", "678", "webufnwmkfe");
    for(String str : lst)
      if (predicate.test(str)) {
        System.out.println(str);
      }
  }
}
