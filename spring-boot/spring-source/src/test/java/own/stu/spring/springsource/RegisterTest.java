package own.stu.spring.springsource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import own.stu.spring.springsource.config.BeanRegisterConfig;
import own.stu.spring.springsource.model.Person;

public class RegisterTest {

  @Test
  public void test_register_with_bean_xml_file(){

    ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
        "config/bean.xml");

//    Person person = classPathXmlApplicationContext.getBean("person", Person.class);
    Person person = (Person) classPathXmlApplicationContext.getBean("person");
    System.out.println(person);
  }

  @Test
  public void test_register_with_config(){

    AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(BeanRegisterConfig.class);
//    Person person = configApplicationContext.getBean(Person.class);
//    Person person = configApplicationContext.getBean("person01", Person.class);
    Person person = (Person) configApplicationContext.getBean("person01");
    System.out.println(person);
  }

}
