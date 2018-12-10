package own.stu.java.concurrence.action.chapter_2;

import net.jcip.annotations.NotThreadSafe;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

@NotThreadSafe
public class UnsafeCachingFactorizer {

    private AtomicReference<Integer> lastNumber = new AtomicReference<>();
    private AtomicReference<String> lastFactors = new AtomicReference<>();

    private void test(Integer i) {

        if (i.equals(lastNumber.get()))
            System.out.println(i + " : " + lastFactors.get());

        else {
            String factors = factor(i);
            lastFactors.set(factors);
            lastNumber.set(i);
            System.out.println(i + " : " + factors);
        }
    }

    String factor(int num) {
        StringBuffer sb = new StringBuffer();
        for (int i = 2; i < num; i++) {
            while (num % i == 0) {
                sb.append(i).append(" - ");
                num = num / i;
            }
        }
        if (num > 1) {
            sb.append(num).append(" - ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(100);
        UnsafeCachingFactorizer stateless = new UnsafeCachingFactorizer();
        for (int i = 0; i < 100; i++)
            service.submit(() -> {
                stateless.test(new Random().nextInt(100));
            });
        service.shutdown();

        /*UnsafeCachingFactorizer stateless = new UnsafeCachingFactorizer();
        stateless.test(90);*/
    }
}
