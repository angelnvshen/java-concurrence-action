package own.jdk.interrupted.demo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrimeGenerator implements Runnable {

    private final List<BigInteger> primes = new ArrayList<>();

    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> getPrimes() {
        return new ArrayList<>(primes);
    }

    //测试程序—— 运行10ms时间的素数生成器
    public static void main(String[] args) throws InterruptedException {
        PrimeGenerator primeGenerator = new PrimeGenerator();

        new Thread(primeGenerator).start();
        TimeUnit.MILLISECONDS.sleep(10);

        primeGenerator.cancel();
        List<BigInteger> ls = primeGenerator.getPrimes();
        for(BigInteger integer : ls){
            System.out.println(integer);
        }
    }
}
