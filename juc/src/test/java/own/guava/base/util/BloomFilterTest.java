package own.guava.base.util;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

public class BloomFilterTest {

    @Test
    public void test() {

        int total = 1000_000;

        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), total);

        for (int i = 0; i < total; i++) {
            filter.put(i);
        }

        for (int i = 0; i < total; i++) {
            if (!filter.mightContain(i)) {
                System.out.println(" someone out which should not be ");
            }
        }

        int count = 0;
        for (int i = total; i < total + 10000; i++) {
            if (filter.mightContain(i)) {
                count++;
            }
        }
        System.out.println("误伤的数量：" + count);
    }
}
