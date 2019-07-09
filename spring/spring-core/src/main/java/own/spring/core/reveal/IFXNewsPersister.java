package own.spring.core.reveal;

public interface IFXNewsPersister {

  void persistNews(FXNewsBean bean);

  FXNewsBean getNewsBean();

}