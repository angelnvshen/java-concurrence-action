package own.stu.sso.jwt;

import java.math.BigInteger;
import org.junit.Test;

public class PureTest {

  @Test
  public void test1(){
    System.out.println(91*123);
    System.out.println(193*11);
    System.out.println(91*123*11);
  }
  @Test
  public void test2(){
    //4000000000000000000000000000001 = 1199481995446957 * 3334772856269093
    System.out.println("4000000000000000000000000000001".length());

    long source = 1234567890L;
    BigInteger num1 = new BigInteger("1234567890");
    BigInteger num2 = new BigInteger("1199481995446957");
    BigInteger num3 = new BigInteger("3334772856269093");
    System.out.println(num1.multiply(num2));
    System.out.println(num1.multiply(num2).multiply(num3));
    System.out.println(num1.multiply(num2).multiply(num3).toString().length());
  }

}
