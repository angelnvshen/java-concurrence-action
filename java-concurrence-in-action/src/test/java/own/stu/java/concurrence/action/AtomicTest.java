package own.stu.java.concurrence.action;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class AtomicTest {

  @Test
  public void AtomicIntegerTest() throws IOException {

    /*int[] source = new int[]{4,5,6,7,8,9};
    int[] target = new int[9];
    System.arraycopy(source, 1, target, 3, 3);
    System.out.println(Arrays.toString(target));

    System.arraycopy(source, 0, target, 0, source.length);
    System.out.println(Arrays.toString(target));

    System.arraycopy(source, 0, target, 3, source.length);
    System.out.println(Arrays.toString(target));*/
    /*FileWriter fileWriter = null;
    BufferedWriter writer = null;
    try {

      fileWriter = new FileWriter("/Users/ScorpionKing/Desktop/sql.txt");
      writer = new BufferedWriter(fileWriter, 16);
      String msg = "1234567890123456789012345678901234567890";
      System.out.println(msg.length());
      writer.write(msg);
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      if(writer != null){
        writer.close();
      }
      if(fileWriter != null){
        fileWriter.close();
      }
    }*/
    System.out.println();
  }

  @Test
  public void testList(){
    List<Integer> list1 = new ArrayList<>();
    list1.add(1);
    list1.add(2);
    list1.add(3);
    list1.add(4);

    System.out.println(list1);
    List<Integer> list2 = new ArrayList<>();
    /*list2.add(2);
    list2.add(6);
    list2.add(3);*/
    System.out.println(list2);


    list1.removeAll(list2);
//    list1.retainAll(list2);

    System.out.println(list1);
    System.out.println(list2);

  }
}
