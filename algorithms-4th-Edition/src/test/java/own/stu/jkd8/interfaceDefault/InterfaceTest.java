package own.stu.jkd8.interfaceDefault;

import org.junit.Test;
import own.stu.jkd8.interfaceDefault.interfaceConflict.Sub2Class;
import own.stu.jkd8.interfaceDefault.parentClassFirst.SubClass;

public class InterfaceTest {

  @Test
  public void testParentClassFirst(){
    SubClass subClass = new SubClass();
    System.out.println(subClass.getName()); // Parent
  }

  @Test
  public void testInterfaceConflict(){
    Sub2Class subClass = new Sub2Class();
    System.out.println(subClass.getName()); // I-HELLO 结果依赖于 类实现了哪一个接口的默认方法
  }

}
