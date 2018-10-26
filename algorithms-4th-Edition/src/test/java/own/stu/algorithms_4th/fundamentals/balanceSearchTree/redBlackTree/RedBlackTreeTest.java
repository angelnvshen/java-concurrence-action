package own.stu.algorithms_4th.fundamentals.balanceSearchTree.redBlackTree;

import org.junit.Test;

public class RedBlackTreeTest {

  @Test
  public void redBlackTreeTest(){

    // SEARCHXMPL
    RedBlackBST tree = new RedBlackBST();

    tree.put("S", "S");
    tree.put("E", "E");
    tree.put("A", "A");
    tree.put("R", "R");
    tree.put("C", "C");
    tree.put("H", "H");
    tree.put("X", "X");
//    tree.put("M", "M");
//    tree.put("P", "P");
//    tree.put("L", "L");

    System.out.println(tree);
//
//    System.out.println(tree.ceiling("B"));
  }


}
