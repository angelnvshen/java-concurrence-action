package own.stu.sourcecore.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import junit.framework.TestCase;
import org.junit.Assert;
import own.stu.sourcecore.junit.Calculation.Calculation;

public class CalculationTest extends TestCase {

  Calculation calculation = null;

  @Override
  protected void setUp() throws Exception {
    calculation = new Calculation();
    System.out.println("在每个测试方法执行前执行--setUp...");
  }

  public void testPrivateMethod(){
    Class<Calculation> clazz = Calculation.class;
    try {
      Method powerMethod = clazz.getDeclaredMethod("power", new Class[]{Double.TYPE, Double.TYPE});
      powerMethod.setAccessible(true);

      Object result = powerMethod.invoke(calculation, new Object[]{5d, 2d});
      System.out.println(result);
      Assert.assertEquals(25d, result);
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail();
    }
  }

  public void testAdd() {
    int result = calculation.add(10, 5);
    Assert.assertEquals(15, result);
  }

  public void testSubtract() {
    System.out.println("测试方法testSubtract...");
    Assert.assertEquals(-1, calculation.subtract(1, 2));// red bar,failure 说明测试没有通过(失败)
  }

  public void testMultiply() {
    System.out.println("测试方法testMultiply...");
    Assert.assertEquals(2, calculation.multiply(1, 2));// blue bar
  }

  public void testDivide() {
    System.out.println("测试方法testDivide...");
    Assert.assertEquals(2, calculation.divide(2, 1));// red bar, error 说明测试程序本身出错
  }

  public void testDivide2() {
    System.out.println("测试方法testDivide2...");
    try{
      int result = calculation.divide(2, 0);
      Assert.fail();
    }catch (Exception e){
      Assert.assertEquals("被除数为0", e.getMessage());
    }
  }

  @Override
  protected void tearDown() throws Exception {

    super.tearDown();
    // cal = null;// 在每个测试方法执行后主动销毁对象
    System.out.println("在每个测试方法执行后执行--tearDown...\n");
  }
}
