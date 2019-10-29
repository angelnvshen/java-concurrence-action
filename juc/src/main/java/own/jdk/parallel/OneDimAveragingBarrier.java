package own.jdk.parallel;

import java.util.stream.DoubleStream;

import static org.junit.Assert.assertSame;

/**
 * index:
 * 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10
 * <p>
 * value:
 * 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1
 * ||
 * 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |0.5| 1
 * ||
 * ...
 * ||
 * 0 |0.1|0.2|0.3|0.4|0.5|0.6|0.7|0.8|0.9| 1
 *
 * Pseudocode：
 *  seq:
 *  for( iter : [0, nSteps -1] ){
 *      for( i : [i, n-1] ){
 *          new[i] = avag(old[i -1], old[i+1])
 *      }
 *      swap(new, old)
 *  }
 *
 *  parallel:
 *  for( iter : [0, nSteps -1] ){
 *      forall( i : [0, n-1] ){
 *          new[i] = avag(old[i -1], old[i+1])
 *      }
 *      swap(new, old)
 *  }
 *  将会有 nstep * n 个task
 *  这时会考虑，（0， n-1)个元素进行分组 （0，ng-1)个组，每个组 n/ng个元素。所有会有nstep * ng个任务
 *
 *
 *
 *
 */
public class OneDimAveragingBarrier {

    private static int n;
    private static double[] myVal;
    private static double[] newVal;

    public static void runSeq(int iterations) {
        for (int i = 0; i < iterations; i++) {
            for (int j = 1; j < n; j++) {
                newVal[j] = (myVal[j - 1] + myVal[j + 1]) / 2;
            }
            double[] tmp = newVal;
            newVal = myVal;
            myVal = tmp;
        }
    }

    public static void main2(String[] args) {
        double[] data = new double[10 + 1];
        System.out.println(data.length);
        data[9] = 1000;

        DoubleStream.of(data).forEach(System.out::println);
    }

    public static void main(String[] args) {
        n = 10;
        myVal = createArray(n);
        newVal = createArray(n);

        myVal[n] = 1.0;
        newVal[n] = 1.0;
        runSeq(4000);

        System.out.println(" ======== ");

//        Arrays.asList(myVal).stream().forEach(System.out::println);
//        Arrays.asList(newVal).stream().forEach(System.out::println);

//        Arrays.stream(myVal).boxed().forEach(System.out::println);
//        Arrays.stream(newVal).boxed().forEach(System.out::println);

        DoubleStream.of(myVal).forEach(System.out::println);
    }

    private void checkResult(final double[] ref, final double[] output) {
        for (int i = 0; i < ref.length; i++) {
            String msg = "Mismatch on output at element " + i;
            assertSame(msg, ref[i], output[i]);
        }
    }

    private static double[] createArray(final int N) {
        final double[] input = new double[N + 1];
        input[N] = 1.0;
        return input;
    }
}
