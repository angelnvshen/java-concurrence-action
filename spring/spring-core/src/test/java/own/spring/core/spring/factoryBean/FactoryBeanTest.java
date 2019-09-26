package own.spring.core.spring.factoryBean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import own.spring.core.config.factoryBean.BeanConfig;
import own.spring.core.part.factoryBean.MilkTeaFactory;

public class FactoryBeanTest {

  @Test
  public void test() throws Exception {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
//    MilkTeaFactory bean = applicationContext.getBean(MilkTeaFactory.class);
//    System.out.println(bean.getObject());

    Object object = applicationContext.getBean("milkTeaFactory");
    System.out.println(object);

    Object object2 = applicationContext.getBean("&milkTeaFactory");
    System.out.println(object2);

    /*Object object2 = applicationContext.getBean("milkTeaFactory");
    System.out.println(object2);*/

/*    Object object2 = applicationContext.getBean("milkTea");
    Object object3 = applicationContext.getBean("oneDot");
    System.out.println(object2);
    System.out.println(object3);*/
  }
}
