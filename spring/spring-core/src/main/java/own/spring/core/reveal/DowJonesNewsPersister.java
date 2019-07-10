package own.spring.core.reveal;

public class DowJonesNewsPersister implements IFXNewsPersister {

  @Override
  public void persistNews(FXNewsBean bean) {

    System.out.println("persistNews : " + bean);
  }

  @Override
  public FXNewsBean getNewsBean() {
    return null;
  }

}