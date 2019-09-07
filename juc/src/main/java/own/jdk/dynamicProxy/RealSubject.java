package own.jdk.dynamicProxy;

/**
 * RealSubject 真实主题类
 *
 * @author
 **/
public class RealSubject implements Subject {

  @Override
  public void doSomething() {
    System.out.println("RealSubject do something");
  }
}