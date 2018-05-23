package own.stu.java.concurrence.action.chapter_7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrimeGenerator implements Runnable {

    private static ExecutorService service = Executors.newCachedThreadPool();

    private final List<BigInteger> primes = new ArrayList<>();
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (! cancelled){
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel(){
        cancelled = true;
    }

    public synchronized List<BigInteger> get(){
        return new ArrayList<>(primes);
    }

    static List<BigInteger> aSencondOfPrimes(){
        PrimeGenerator genetor = new PrimeGenerator();
        service.submit(genetor);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            genetor.cancel();
        }
        return genetor.get();
    }

    public static void main(String[] args) {
        List<BigInteger> lst = PrimeGenerator.aSencondOfPrimes();
        for(BigInteger i : lst)
            System.out.println(i);
    }
}
