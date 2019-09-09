package own.spring.core;

import static org.junit.Assert.assertTrue;

import com.sun.tools.classfile.Dependencies;
import java.util.Arrays;
import javax.swing.Spring;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.TransactionTemplate;
import own.spring.core.config.BeanConfig;
import own.spring.core.model.Book;
import own.spring.core.model.BookFactoryBean;
import own.spring.core.model.Person;
import own.spring.core.service.MessageService;
import sun.net.www.content.text.Generic;
import sun.net.www.content.text.plain;

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

    Arrays.asList(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

    // applicationContext.getAutowireCapableBeanFactory()
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
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
    Book book = applicationContext.getAutowireCapableBeanFactory().createBean(Book.class);
    System.out.println(book);

    System.out.println("----- ");

    BeanDefinition beanDefinition = ((AnnotationConfigApplicationContext) applicationContext)
        .getBeanDefinition("messageService");
    System.out.println(beanDefinition);

    for (String name : applicationContext.getBeanDefinitionNames()) {
      System.out.println(name);
    }

    System.out.println("----- ");

    RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Book.class);
    ((AnnotationConfigApplicationContext) applicationContext).registerBeanDefinition("booker", rootBeanDefinition);

    for (String name : applicationContext.getBeanDefinitionNames()) {
      System.out.println(name);
    }

    Object booker = applicationContext.getBean("booker");
    System.out.println(booker);

    booker = applicationContext.getBean("booker");
    System.out.println(booker);
  }

  @Test
  public void testFactoryBean(){
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);

    for (String name : applicationContext.getBeanDefinitionNames()) {
      System.out.println(name);
    }

    System.out.println(" ======= ");

    Object bookFactoryBean = applicationContext.getBean("bookFactoryBean");
    Book bean = applicationContext.getBean(Book.class);
    System.out.println(bean + " - " + bookFactoryBean + " - " + bean.equals(bookFactoryBean));

    System.out.println(" ======= ");

    Object bean1 = applicationContext.getBean("&bookFactoryBean");
    BookFactoryBean bean2 = applicationContext.getBean(BookFactoryBean.class);

    System.out.println(bean1 + " - " + bean2 + " - " + bean1.equals(bean2));
  }

  @Test
  public void testInitAndDestroy(){

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);

    BeanDefinition beanDefinition = ((AnnotationConfigApplicationContext) applicationContext).getBeanDefinition("car");
    System.out.println(beanDefinition);

    ((AnnotationConfigApplicationContext) applicationContext).close();
  }


  @Test
  public void testDestructionAwareBeanProcessor(){
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
    ((AnnotationConfigApplicationContext) applicationContext).close();
  }
  @Test
  public void testBean(){
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
    Person bean = applicationContext.getBean(Person.class);
    System.out.println(bean);
  }


}
