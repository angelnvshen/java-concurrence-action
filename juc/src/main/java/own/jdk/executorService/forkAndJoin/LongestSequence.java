package own.jdk.executorService.forkAndJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 给定字符 最长序列长度
 * <p>
 * For example, if arr is{2,17,17,8,17,17,17,0,17,1} then longest sequence(17,arr) is 3
 * and longest sequence(9,arr) is 0
 */
public class LongestSequence {

    ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();

    private List<Integer> data = new ArrayList<>();

    class WorkRequest extends RecursiveTask<Result>{



        @Override
        protected Result compute() {
            return null;
        }
    }

    class Result {

        int numLeftEdge;
        int numRightEdge;
        int numLongest;
        boolean entireRange;

        Result(int l, int r, int m, boolean a) {
            numLeftEdge = l;
            numRightEdge = r;
            numLongest = m;
            entireRange = a;
        }
    }
}
