package own.stu.sourcecore.genericType.distribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import own.stu.sourcecore.distribution.ConsistentHash;

public class ConsistentHashTest {

  @Test
  public void test(){
    ConsistentHash<String> hash = new ConsistentHash(
        3, Lists.newArrayList("N1", "N2", "N3"));

    Map<String, List<Integer>> numMap = getStringListMap(hash);
    System.out.println(numMap);

    hash.add("N4");
    Map<String, List<Integer>> numMap2 = getStringListMap(hash);
    System.out.println(numMap2);

    hash.remove("N4");
    Map<String, List<Integer>> numMap3 = getStringListMap(hash);
    System.out.println(numMap3);
  }

  private Map<String, List<Integer>> getStringListMap(ConsistentHash<String> hash) {
    Map<String, List<Integer>> numMap = new HashMap<>();
    for(int i =0;i < 100; i++){
      String node = hash.get(i);
//      System.out.println(String.format("%d -> %s", i, node));
      List<Integer> valueList = numMap.get(node);
      if(CollectionUtils.isEmpty(valueList)){
        valueList = new ArrayList<>();
        numMap.put(node, valueList);
      }
      valueList.add(i);
    }
    return numMap;
  }

  @Data
  class ValueNode {

    private Integer value;

    private String node;

    public ValueNode(Integer value, String node) {
      this.value = value;
      this.node = node;
    }
  }

}
