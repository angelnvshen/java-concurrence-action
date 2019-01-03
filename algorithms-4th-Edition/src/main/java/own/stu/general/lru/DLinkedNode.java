package own.stu.general.lru;

import lombok.Data;

@Data
public class DLinkedNode {

  String key;
  int value;
  DLinkedNode pre;
  DLinkedNode post;
}
