package own.spring.core.reveal;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DowJonesNewsListener implements IFXNewsListener {

  public DowJonesNewsListener() {
  }

  private static Map<String, FXNewsBean> map = new HashMap<>();

  static {
    map.put("1", new FXNewsBean("feirri"));
    map.put("2", new FXNewsBean("vivo"));
  }

  @Override
  public String[] getAvailableNewsIds() {
    return new String[]{"1", "2"};
  }

  @Override
  public FXNewsBean getNewsByPK(String newsId) {
    return map.get(newsId);
  }

  @Override
  public void postProcessIfNecessary(String newsId) {

  }
}