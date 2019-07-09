package own.spring.core.reveal;

public class DowJonesNewsListener implements IFXNewsListener {

  @Override
  public String[] getAvailableNewsIds() {
    return new String[0];
  }

  @Override
  public FXNewsBean getNewsByPK(String newsId) {
    return null;
  }

  @Override
  public void postProcessIfNecessary(String newsId) {

  }
}