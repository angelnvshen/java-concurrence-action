package own.stu.spring.springsource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Controller;
import own.stu.spring.springsource.config.BeanRegisterConfig;
import own.stu.spring.springsource.config.MyFactoryBean;
import own.stu.spring.springsource.less.controller.BookController;
import own.stu.spring.springsource.model.City;
import own.stu.spring.springsource.model.Person;

public class RegisterTest {

  @Test
  public void test_register_with_bean_xml_file() {

    ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
        "config/bean.xml");

//    Person person = classPathXmlApplicationContext.getBean("person", Person.class);
    Person person = (Person) classPathXmlApplicationContext.getBean("person");
    System.out.println(person);
  }

  @Test
  public void test_register_with_config() {

    AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(
        BeanRegisterConfig.class);
//    Person person = configApplicationContext.getBean(Person.class);
//    Person person = configApplicationContext.getBean("person01", Person.class);
    Person person = (Person) configApplicationContext.getBean("person01");
    System.out.println(person);
  }

  // =====================================
  // =====================================

  @Test
  public void test_register_with_bean_xml_file_component_scan() {

    ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
        "config/bean.xml");

    for (String name : classPathXmlApplicationContext.getBeanDefinitionNames()) {
      System.out.println(name);
    }

//    Person person = classPathXmlApplicationContext.getBean("person", Person.class);
    BookController bookController = (BookController) classPathXmlApplicationContext.getBean("bookController");
    System.out.println(bookController);
  }

  @Test
  public void test_register_with_config_component_scan() {
    AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(
        BeanRegisterConfig.class);

    for (String name : configApplicationContext.getBeanDefinitionNames()) {
      System.out.println(name);
    }

    Person person = configApplicationContext.getBean(Person.class);
    System.out.println(person);

    BookController bookController = configApplicationContext.getBean(BookController.class);
    System.out.println(bookController);

  }

  @Test
  public void test() {
    System.out.println(Controller.class.getSimpleName());
    System.out.println(Controller.class.getCanonicalName());
  }

  @Test
  public void test_scope() {
    AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(
        BeanRegisterConfig.class);

    System.out.println("start");

    City city = configApplicationContext.getBean(City.class);
    System.out.println(city);

    city = configApplicationContext.getBean(City.class);
    city = configApplicationContext.getBean(City.class);
  }

  @Test
  public void test_conditional() {

    AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(
        BeanRegisterConfig.class);

    for (String name : configApplicationContext.getBeanNamesForType(Person.class)) {

      System.out.println(name);
    }
  }

  @Test
  public void test_import() {
    AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(
        BeanRegisterConfig.class);

    for (String name : configApplicationContext.getBeanDefinitionNames()) {

      System.out.println(name);
    }
  }

  @Test
  public void test_factoryBean() {
    AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(
            BeanRegisterConfig.class);

    MyFactoryBean myFactoryBean = configApplicationContext.getBean(MyFactoryBean.class);
    System.out.println(myFactoryBean);

    Object bean = configApplicationContext.getBean("myFactoryBean");
    System.out.println(bean.getClass());

    Object bean2 = configApplicationContext.getBean("&myFactoryBean");
    System.out.println(bean2.getClass());
  }
}
