package own.jdk.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SychronizeQueueTest {

    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

    }

    public static void main2(String[] args) {

        List<Integer> collect = IntStream.range(0, 10).boxed().collect(Collectors.toList());

        ArrayBlockingQueue<Integer> objects = new ArrayBlockingQueue<>(10);
        objects.addAll(collect);

        collect = new ArrayList<>();
        objects.drainTo(collect);

        System.out.println(collect);
        System.out.println(objects);
    }
}
