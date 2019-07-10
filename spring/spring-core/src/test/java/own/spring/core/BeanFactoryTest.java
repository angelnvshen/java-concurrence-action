package own.spring.core;

import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import own.spring.core.reveal.DowJonesNewsListener;
import own.spring.core.reveal.DowJonesNewsPersister;
import own.spring.core.reveal.FXNewsProvider;

public class BeanFactoryTest {

  @Test
  public void test(){

    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    bindViaCode(beanFactory);
    FXNewsProvider fxNewsProvider = beanFactory.getBean(FXNewsProvider.class);
    fxNewsProvider.getAndPersistNews();
  }

  public static BeanFactory bindViaCode(BeanDefinitionRegistry registry){
    AbstractBeanDefinition newsProvider = new RootBeanDefinition(FXNewsProvider.class);
    AbstractBeanDefinition newsListener = new RootBeanDefinition(DowJonesNewsListener.class);
    AbstractBeanDefinition newsPersister = new RootBeanDefinition(DowJonesNewsPersister.class);

    // 将bean定义注册到容器中
    registry.registerBeanDefinition("djNewsProvider", newsProvider);
    registry.registerBeanDefinition("djListener", newsListener);
    registry.registerBeanDefinition("djPersister", newsPersister);

    // 指定依赖关系
    // 1：通过构造方法注入
    ConstructorArgumentValues argumentValues = new ConstructorArgumentValues();
    argumentValues.addIndexedArgumentValue(0, newsListener);
    argumentValues.addIndexedArgumentValue(1, newsPersister);
    newsProvider.setConstructorArgumentValues(argumentValues);

    // 2：通过set方法注入
    MutablePropertyValues propertyValues = new MutablePropertyValues();
    propertyValues.addPropertyValue("newsListener", newsListener);
    propertyValues.addPropertyValue("newPersistener", newsPersister);
    newsProvider.setPropertyValues(propertyValues);

    return (BeanFactory) registry;
  }

}
