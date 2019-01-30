package own.stu.sourcecore.junit;

import java.util.HashMap;
import java.util.Map;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONUtil;
import org.assertj.core.util.Lists;

public class PureTest {

  public static void main(String[] args) {
//    junit.textui.TestRunner.run(CalculationTest.class);

    Map map = new HashMap();
    map.put("12", "32232");
    map.put("we", "32232");
    map.put("223", Lists.newArrayList("2", "3"));
    map.put("44", Lists.newArrayList());
    System.out.println(JSONObject.toJSONString(map));

    System.out.println("310111195410231232".length());
  }
}
