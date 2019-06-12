package own.stu.spring.springsource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import own.stu.spring.springsource.config.BeanRegisterConfig;
import own.stu.spring.springsource.less.controller.BookController;
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

    BookController bookController = configApplicationContext.getBean(BookController.class);
    System.out.println(bookController);

    Person person = configApplicationContext.getBean(Person.class);
    System.out.println(person);
  }
}
