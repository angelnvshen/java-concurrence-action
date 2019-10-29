package own.jdk.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SimpleArraySum {

    public static double seqArraySum(Double[] X) {
        long startTime = System.currentTimeMillis();
        double sum = 0;
        for (int i = 0; i < X.length; i++) {
            sum += 1 / X[i];
        }
        long cost = System.currentTimeMillis() - startTime;
        printResult("seqArraySum", cost, sum);
        return sum;
    }

    public static double parArraySum(Double[] X) {
        long startTime = System.currentTimeMillis();

        SumArray t = new SumArray(0, X.length, X);
        double sum = ForkJoinPool.commonPool().invoke(t);
        long cost = System.currentTimeMillis() - startTime;
        printResult("parArraySum", cost, sum);
        return sum;
    }

    public static void printResult(String msg, long cost, double sum) {
        System.out.printf("     %s completed in %d milliseconds, with sum = %f \n", msg, cost, sum);
    }

    public static void main(String[] args) {
        int times = 4;
        Double[] doubles = DataGene.geneDoubleArrays(50_000_000);
        for (int i = 0; i < times; i++) {
            System.out.println("run " + i);
            seqArraySum(doubles);
            parArraySum(doubles);
        }
    }

    private static class SumArray extends RecursiveTask<Double> {

        private static int threshold = 50000;

        private int start;
        private int end;
        private Double[] data;

        public SumArray(int start, int end, Double[] data) {
            this.start = start;
            this.end = end;
            this.data = data;
        }

        @Override
        protected Double compute() {

            if (end - start <= threshold) {
                double sum = 0;
                for (int i = start; i < end; i++) {
                    sum += 1 / data[i];
                }
                return sum;
            } else {
                SumArray left = new SumArray(start, (end + start) / 2, data);
                SumArray right = new SumArray((end + start) / 2, end, data);

                left.fork();
                double rightSum = right.compute();
                double leftSum = left.join();
                return rightSum + leftSum;
            }
        }
    }
}
