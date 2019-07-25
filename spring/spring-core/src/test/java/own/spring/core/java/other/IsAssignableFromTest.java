package own.spring.core.java.other;

import org.junit.Test;

public class IsAssignableFromTest {

  /**
   * class1.isAssignableFrom(class2)
   *  判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口
   */
  @Test
  public void test() {

    A a = new A();
    B b = new B();
    A ba = new B();
    System.out.println("1-------------");   // 1-------------
    System.out.println(A.class.isAssignableFrom(a.getClass()));   // true
    System.out.println(B.class.isAssignableFrom(b.getClass()));   // true
    System.out.println(A.class.isAssignableFrom(b.getClass()));   // true
    System.out.println(B.class.isAssignableFrom(a.getClass()));   // false
    System.out.println(A.class.isAssignableFrom(ba.getClass()));   // true
    System.out.println(B.class.isAssignableFrom(ba.getClass()));   // true
    System.out.println("2-------------");   // 2-------------
    System.out.println(a.getClass().isAssignableFrom(A.class));   // true
    System.out.println(b.getClass().isAssignableFrom(B.class));   // true
    System.out.println(a.getClass().isAssignableFrom(B.class));   // true
    System.out.println(b.getClass().isAssignableFrom(A.class));   // false
    System.out.println(ba.getClass().isAssignableFrom(A.class));   // false
    System.out.println(ba.getClass().isAssignableFrom(B.class));   // true
    System.out.println("3-------------");   // 3-------------
    System.out.println(Object.class.isAssignableFrom(b.getClass()));   // true
    System.out.println(Object.class.isAssignableFrom("abc".getClass()));   // true
    System.out.println("4-------------");   // 4-------------
    System.out.println("a".getClass().isAssignableFrom(Object.class));   // false
    System.out.println("abc".getClass().isAssignableFrom(Object.class));   // false
  }

  class A {

  }

  class B extends A {

  }
}