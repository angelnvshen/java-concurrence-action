package own.spring.core.part.factoryBean;

import org.springframework.beans.factory.FactoryBean;

public class MilkTeaFactory implements FactoryBean<MilkTea> {

  @Override
  public MilkTea getObject() throws Exception {
    System.out.println("getObject ... ");
    MilkTea milkTea = new MilkTea();
    milkTea.setName("一点点");
    return milkTea;
  }

  @Override
  public Class<?> getObjectType() {
    return MilkTea.class;
  }

  // 是否是单例
  @Override
  public boolean isSingleton() {
    return true;
  }
}
