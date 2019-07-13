package own.spring.core;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues.ValueHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import own.spring.core.config.BeanFactoryPostProcessorConfig;
import own.spring.core.config.InjectBeanConfig;
import own.spring.core.model.inject.Cup;
import own.spring.core.reveal.DowJonesNewsListener;
import own.spring.core.reveal.DowJonesNewsPersister;
import own.spring.core.reveal.FXNewsProvider;

public class BeanFactoryPostProcessorTest {

  @Test
  public void testPropertyPlaceHolderConfigurer(){

    ApplicationContext context = new AnnotationConfigApplicationContext(BeanFactoryPostProcessorConfig.class);
    BasicDataSource basicDataSource = context.getBean(BasicDataSource.class);

    System.out.println(basicDataSource);
    System.out.println(basicDataSource.getUrl());
    System.out.println(basicDataSource.getDriverClassName());
    System.out.println(basicDataSource.getMaxTotal());
  }


}
