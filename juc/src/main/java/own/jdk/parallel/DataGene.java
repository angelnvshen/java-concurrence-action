package own.jdk.parallel;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGene {
    private static Random random = new Random(100);

    public static Double[] geneDoubleArrays(int size) {

        Double[] result = new Double[size];
        Stream.generate(() -> random.nextDouble()).limit(size).collect(Collectors.toList()).toArray(result);
        return result;
    }

    // TODO 并行|并行分块|barrier in parallel loop
    public static Double[][] geneTwoDimDoubleArrays(int x, int y) {
        Double[][] result = new Double[x][y];



        return result;
    }
}
