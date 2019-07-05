package own.stu.spring.springsource.model;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Data
public class City implements Serializable,InitializingBean, DisposableBean {

  private String name;

  public City(String name) {
    System.out.println("construct city ... ");
    this.name = name;
  }

  public City() {
  }

  public void init() {
    System.out.println("city .. init .. ");
  }

  public void destroy_1() {
    System.out.println("city .. destroy .. ");
  }

  public void destroy() {
    System.out.println("city .. DisposableBean .. ");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("city ..InitializingBean .... ");
  }

  @PostConstruct
  public void postConstruct(){
    System.out.println("city ..PostConstruct ... ");
  }

  @PreDestroy
  public void preDestroy(){
    System.out.println("city ..preDestroy ... ");
  }
}
