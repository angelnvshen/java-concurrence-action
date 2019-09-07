package own.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.ImmutableBean;
import net.sf.cglib.proxy.CallbackHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.NoOp;
import org.junit.Assert;
import org.junit.Test;

public class HelloCglibTest {

  @Test
  public void testHelloWorld() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(HelloCglib.class);
    enhancer.setCallback((MethodInterceptor) (obj, method, objects, methodProxy) -> {
      System.out.println("before method run...");
      Object result = methodProxy.invokeSuper(obj, objects);
      System.out.println("after method run...");
      return result;
    });

    HelloCglib o = (HelloCglib) enhancer.create();
    o.test();
  }

  @Test
  public void testFixedValue() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(HelloCglib.class);
    enhancer.setCallback((FixedValue) () -> "hello cglib");

    HelloCglib o = (HelloCglib) enhancer.create();
    o.test();
    System.out.println(o.test("x"));
    System.out.println(o.toString());
    System.out.println(o.getClass());
    System.out.println(o.hashCode());
  }

  @Test
  public void testCallbackHelper() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(HelloCglib.class);

    CallbackHelper helper = new CallbackHelper(HelloCglib.class, new Class[0]) {
      @Override
      protected Object getCallback(Method method) {
        if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
          return (FixedValue) () -> "hello cg";
        }
        return NoOp.INSTANCE;
      }
    };
    enhancer.setCallbackFilter(helper);
    enhancer.setCallbacks(helper.getCallbacks());

    HelloCglib o = (HelloCglib) enhancer.create();
    o.test();
    System.out.println(o.test("x"));
    System.out.println(o.toString());
    System.out.println(o.getClass());
    System.out.println(o.hashCode());
  }

  @Test
  public void testImmutableBean() {
    SampleBean bean = new SampleBean();
    bean.setValue("Hello world");

    SampleBean immutableBean = (SampleBean) ImmutableBean.create(bean);
    Assert.assertEquals("Hello world",immutableBean.getValue());

    bean.setValue("Hello world, again"); //可以通过底层对象来进行修改
    Assert.assertEquals("Hello world, again", immutableBean.getValue());

    immutableBean.setValue("Hello cglib"); //直接修改将throw exception

  }

  @Test
  public void testBeanGenerator() throws Exception{

    BeanGenerator generator = new BeanGenerator();
    generator.addProperty("value", String.class);

    Object o = generator.create();
    Method setValue = o.getClass().getMethod("setValue", String.class);
    setValue.invoke(o, "helloCg");

    Method getValue = o.getClass().getMethod("getValue");
    System.out.println(getValue.invoke(o, null));
  }
}
