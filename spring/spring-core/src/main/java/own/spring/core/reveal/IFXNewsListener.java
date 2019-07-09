package own.spring.core.reveal;

public interface IFXNewsListener {

  String[] getAvailableNewsIds();

  FXNewsBean getNewsByPK(String newsId);

  void postProcessIfNecessary(String newsId);
}