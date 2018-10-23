package own.stu.jkd8.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.junit.Test;
import own.stu.jkd8.model.Employee;

/**
 * 一：方法引用：若lambda体中的内容已经有方法实现了，我们可以使用"方法引用"
 *          （可以理解为lambda表达式的另一种表现形式）
 *    主要有三种语法形式：
 *    对象::实例方法名
 *    类名::静态方法名
 *    类名::实例方法名
 *
 *    注意：
 *    1：Lambda 体中调用方法的参数列表和返回值类型，要和函数式接口中的抽象方法的参数列表和返回值类型一致
 *    2：若Lambda参数列表中的第一个参数是实例方法的调用者，第二个参数是实例方法的参数时，可以使用 类名::实例方法名
 *
 * 二：构造器引用
 *    ClassName::new
 *
 *    注意:
 *    需要调用的构造函数的参数列表要与函数式接口中的抽象方法的参数列表一致
 *
 * 三：数组引用
 *    Type[]::new
 *
 */
public class MethodRefTest {

  //构造器引用
  @Test
  public void test5(){
    Function<Integer, String[]> function = (x) -> new String[x];
    System.out.println(function.apply(10).length);

    Function<Integer, String[]> function2 = String[]::new;
    System.out.println(function2.apply(10).length);
  }

  //构造器引用
  @Test
  public void test4(){
    Supplier supplier = () -> new Employee();
    System.out.println(supplier.get());

    Supplier supplier1 = Employee::new ;
    System.out.println(supplier1.get());
  }

  //类名::实例方法名
  @Test
  public void test3(){
    BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x.compareTo(y);
    System.out.println(biFunction.apply(9,10));

    BiFunction<Integer, Integer, Integer> biFunction2 = Integer::compareTo;
    System.out.println(biFunction2.apply(9,12));
  }

  //类名::静态方法名
  @Test
  public void test2(){
    Function<Integer, String> function = (x) -> Integer.toHexString(x);
    System.out.println(function.apply(100));

    Function<Integer, String> function2 = Integer::toHexString;
    System.out.println(function2.apply(100));

    BiFunction<Integer, Integer, Integer> function3 = Integer::compare;
    System.out.println(function3.apply(100, 200));

  }

  //对象::实例方法名
  @Test
  public void test1(){
    Consumer consumer = (x) -> System.out.println(x);
    consumer.accept("ewijfjwfw");

    Consumer consumer2 = System.out::println;
    consumer2.accept("ewijfjwfw");

  }

}
