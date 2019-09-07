package own.spring.core.reveal;

import org.springframework.stereotype.Component;

@Component
public class DowJonesNewsPersister implements IFXNewsPersister {

  public DowJonesNewsPersister() {
  }

  @Override
  public void persistNews(FXNewsBean bean) {

    System.out.println("persistNews : " + bean);
  }

  @Override
  public FXNewsBean getNewsBean() {
    return null;
  }

}