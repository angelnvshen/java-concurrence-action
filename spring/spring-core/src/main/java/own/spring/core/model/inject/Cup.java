package own.spring.core.model.inject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class Cup implements BeanFactoryAware {

  BeanFactory beanFactory;

  private String name;

  @Autowired
  private Water water;

  private Tea tea;

  @Autowired
  JuiceObjectFactoryCreatingFactoryBean juiceObjectFactoryCreatingFactoryBean;

  /**
   * 所以在进行方法注入时，需要注意注入的方法的标准格式如下： <public|protected> [abstract] <return-type> theMethodName(no-arguments);
   */
  // @Lookup
  public Water getWater() {
    System.out.println();
    return water;
  }

  public void setWater(Water water) {
    this.water = water;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Tea getTea() {
    return beanFactory.getBean(Tea.class);
  }

  public void setTea(Tea tea) {
    this.tea = tea;
  }

  public Juice getJuice() {
    try {
      Juice juice = (Juice) juiceObjectFactoryCreatingFactoryBean.getObject();
      return juice;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }
}
