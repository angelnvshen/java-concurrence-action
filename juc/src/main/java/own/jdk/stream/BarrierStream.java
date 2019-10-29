package own.jdk.stream;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *  并行编程 with barrier
 */
public class BarrierStream {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        ArrayList<String> strings = Lists.newArrayList("1", "2", "3");
        strings
                .parallelStream()
                .forEach(s -> {
                    System.out.println("hello " + s);
                    try {
                        cyclicBarrier.await(); // print hello *** first ; then print bye ***
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("bye " + s);
                });

    }
}
