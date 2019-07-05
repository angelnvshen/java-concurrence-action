package own.spring.core;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import own.spring.core.config.BeanConfig;
import own.spring.core.service.MessageService;

/**
 * Unit test for simple App.
 */
public class AppTest {

  /**
   * Rigorous Test :-)
   */
  @Test
  public void shouldAnswerWithTrue() {
    assertTrue(true);

  }


  @Test
  public void testAnnotationApplicationContext() {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);

    MessageService messageService = applicationContext.getBean(MessageService.class);
    System.out.println(messageService.getMessage());

//    applicationContext.getBeanNamesForType()
    BeanFactory parentBeanFactory = applicationContext.getParentBeanFactory();
    System.out.println(parentBeanFactory);
  }

  @Test
  public void testBeanFactoryMethodName() {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);

    MessageService messageService = applicationContext.getBean(MessageService.class);
    System.out.println(messageService.getMessage());

    String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
    Arrays.asList(beanDefinitionNames).forEach(System.out::println);

    Object bean = applicationContext.getBean("messageService");
    System.out.println(bean.getClass().getName());
    MessageService bean1 = applicationContext.getBean(MessageService.class);
    System.out.println(bean1.getMessage());

    System.out.println("application context contain bean : " + applicationContext.containsBean("messageService"));
    System.out.println("is singleton bean : " + applicationContext.isSingleton("messageService"));
    System.out.println("is isPrototype bean : " + applicationContext.isPrototype("messageService"));
    System.out.println("is typeMatch bean : " + applicationContext.isTypeMatch("messageService", MessageService.class));
    System.out.println("get bean type : " + applicationContext.getType("messageService"));
  }

  @Test
  public void testListableBeanFactoryMethodName() {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
    System.out.println(applicationContext.containsBeanDefinition("messageService"));
    System.out.println(applicationContext.getBeanDefinitionCount());
    String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
    System.out.println(beanDefinitionNames);
    Configuration annotationOnBean = applicationContext.findAnnotationOnBean("beanConfig", Configuration.class);
    System.out.println(annotationOnBean);
  }

  @Test
  public void testAutowireCapableBeanFactoryMethodName() {

  }
}
