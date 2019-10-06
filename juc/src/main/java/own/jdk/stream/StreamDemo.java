package own.jdk.stream;


import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;

public class StreamDemo {

  public static void main(String[] args) {
    /*ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6);

    long count = list.stream()
        .count();
    System.out.println(count);

    Spliterator<Integer> spliterator = list.spliterator();
    spliterator.forEachRemaining(v -> System.out.println(v));*/

    List<String> data = new ArrayList<>();
    data.add("张三");
    data.add("李四");
    data.add("王三");
    data.add("马六");

    data.stream()
        .filter(x -> x.length() == 2)
        .map(x -> x.replace("三", "五"))
//        .sorted()
//        .filter(x -> x.contains("五"))
        .forEach(System.out::println);

//    System.out.println(0b10);
//    System.out.println(031);

    /*for (StreamOpFlag.Type type : StreamOpFlag.Type.values()) {
      System.out.println(type);
    }
    System.out.println();*/
//    for(StreamOpFlag flag : StreamOpFlag.values()){
//      System.out.println(flag);
//    }

//    ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6);
//    list.forEach(System.out::println);
  }
}


