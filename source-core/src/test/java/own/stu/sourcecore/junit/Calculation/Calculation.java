package own.stu.sourcecore.junit.Calculation;

public class Calculation {

  public int add(int a, int b){
    return a + b;
  }

  public int subtract(int a, int b){
    return a - b;
  }

  public int multiply(int a, int b){
    return a * b;
  }

  public int divide(int a, int b){
    if(b == 0){
      throw new RuntimeException("被除数为0");
    }
    return a / b;
  }

  private double power(double a, double b){
    return Math.pow(a, b);
  }
}
