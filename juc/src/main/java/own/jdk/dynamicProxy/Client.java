package own.jdk.dynamicProxy;

public class Client {

  public static void main(String[] args) {

    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

    JDKDynamicProxy proxy = new JDKDynamicProxy(new RealSubject());
    Subject subject = proxy.getProxy();
    subject.doSomething();
  }
}
