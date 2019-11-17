package own.jdk.coure.algo.analysis.heap;

import org.junit.Test;
import own.leetcode.binaryTreeAndGraph.analysis.heap.HeapSort;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.stream.IntStream;

public class PriorityQueueTest {

    Random random = new Random();

    @Test
    public void test() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.add(random.nextInt(10));
        }
        System.out.println(queue);
    }

    @Test
    public void testHeapSort() {
        int max = 65535;
        Random random = new Random();
        Integer[] ints = new Integer[max];
        for (int i = 0; i < max; i++) {
            ints[i] = random.nextInt(65535);
        }
        HeapSort<Integer> heapSort = new HeapSort<>();
        heapSort.heapSort(ints);

        for (int i = 0; i < max - 1; i++) {
            assert ints[i] <= ints[i + 1];
        }
    }

    @Test
    public void testGene2() {

    }


    public int[] testGene(int len, int seed) {
        Random random = new Random();
        int[] ints = IntStream.generate(() -> random.nextInt(seed)).limit(len).toArray();
        return ints;
    }
}
