package own.spring.core;

import org.junit.Test;

public class BasicTest {

  // ??
  @Test
  public void test_String_intern(){

    String str1 = new StringBuffer("计算机").append("科学").toString();
    System.out.println(str1 == str1.intern());

    String str2 = new StringBuffer("ja").append("va").toString();
    System.out.println(str2 == str2.intern());

    String str3 = "计算机科学";
    System.out.println(str3 == str1.intern());

    String str4 = "java";
    System.out.println(str4 == str2.intern());
  }
}
