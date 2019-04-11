package own.stu.algorithms_4th.string;

import edu.princeton.cs.algs4.In;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

public class StringTest {

  String str = "";

  @Before
  public void init() {
    String file = "/Users/ScorpionKing/Desktop/sql-.txt";
    In in = new In(file);
    str = in.readAll();
  }

  @Test
  public void test_subString() {

    List<String> list = new ArrayList<>();
    long start = System.currentTimeMillis();
    for (int i = 0; i < str.length(); i++) {
      list.add(str.substring(i, str.length()));
    }
    System.out.println(System.currentTimeMillis() - start);

    List<String> list2 = new ArrayList<>();
    StringBuilder sb = new StringBuilder(str);
    long start2 = System.currentTimeMillis();
    for (int i = 0; i < str.length(); i++) {
      list2.add(sb.substring(i, str.length()));
    }
    System.out.println(System.currentTimeMillis() - start2);

    System.out.println("xxx");
  }

  @Test
  public void test_key_index() {

    List<Student> list = Lists
        .newArrayList(new Student("Anderson", 2), new Student("Brown", 3), new Student("Davis", 3),
            new Student("Garcia", 4), new Student("Harris", 1), new Student("Jackson", 3), new Student("Johnson", 4),
            new Student("Jones", 3), new Student("Martin", 1), new Student("Martinez", 2), new Student("Miller", 2),
            new Student("Moore", 1), new Student("Robinson", 2), new Student("Smith", 4), new Student("Taylor", 3),
            new Student("Thomas", 4), new Student("Thompson", 4), new Student("White", 2), new Student("Williams", 3),
            new Student("Wilson", 4));

    int N = list.size();
    Student[] students = new Student[N];
    list.toArray(students);
    printArray(students);
    Student[] aux = new Student[N];
    int R = 5;
    Integer[] count = new Integer[R + 1];
    for (int i = 0; i <= R; i++) {
      count[i] = 0;
    }
    // 算计出现的频率
    for (int i = 0; i < N; i++) {
      count[students[i].getGroup() + 1]++;
    }
    printArray(count);
    // 将频率转为索引
    for (int i = 0; i < R; i++) {
      count[i + 1] += count[i];
    }
    printArray(count);
    // 将元素分类
    for (int i = 0; i < N; i++) {
      aux[count[students[i].getGroup()]++] = students[i];
    }
    printArray(count);
    // 回写
    for (int i = 0; i < N; i++) {
      students[i] = aux[i];
    }
    printArray(students);

  }

  @Test
  public void test_LSD() {
    String[] str = {"4PGC938", "2IYE230", "3CIO720", "1ICK750", "1OHV845", "4JZY524", "1ICK750", "3CIO720", "1OHV845",
        "1OHV845", "2RLA629", "2RLA629", "3ATW723"};
    LSD.sort(str, 7);
    printArray(str);
  }

  @Test
  public void test2() {
    int[] nums = new int[2];
    for (int i = 0; i < 10; i++) {
      nums[0]++;
    }
    System.out.println(nums);
  }

  private <T> void printArray(T[] t) {
    for (int i = 0; i < t.length; i++) {
      System.out.print(t[i] + " ");
    }
    System.out.println();
  }

  @Data
  public static class Student {

    private String name;
    private int group;

    public Student(String name, int group) {
      this.name = name;
      this.group = group;
    }

    public int key() {
      return group;
    }
  }
}

