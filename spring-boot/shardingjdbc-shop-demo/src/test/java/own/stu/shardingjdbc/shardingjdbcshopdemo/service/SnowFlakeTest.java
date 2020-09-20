package own.stu.shardingjdbc.shardingjdbcshopdemo.service;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class SnowFlakeTest {

    private int getPowerOfTwo(int n) {
        n -= 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;

        return n < 0 ? 1 : (n > (1 << 30)) ? (1 << 30) : n + 1;
    }

    public boolean isPowerOfFour(int num) {
        if(num < 0) return false;
        int n = getPowerOfTwo(num);
        return n == num && ((num & 0xaaaaaaaa) == 0);
    }

    @Test
    public void test3() {
        for (int i = 0; i < 34; i++)
            System.out.println(isPowerOfFour(i) + " -> " + i);
//        System.out.println(456245558764371968L >> 22);
//        System.out.println(456245558764372368L >> 22);
    }

    private int powerOfFour(int n) {

        return 0;
    }

    @Test
    public void test1() throws InterruptedException {
        SnowflakeShardingKeyGenerator shardingKeyGenerator = new SnowflakeShardingKeyGenerator();
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add((Long) shardingKeyGenerator.generateKey());
        }
        TimeUnit.SECONDS.sleep(1);
        for (int i = 0; i < 10; i++) {
            list.add((Long) shardingKeyGenerator.generateKey());
        }

        System.out.println(list);

        Map<Long, List<Long>> collect = list.stream().collect(Collectors.groupingBy(t -> t >> 22));
        System.out.println(collect);
    }

    @Test
    public void test() throws InterruptedException {

        ThreadPoolExecutor generetor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        generetor.prestartAllCoreThreads();

        ThreadPoolExecutor cyclier = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        cyclier.prestartAllCoreThreads();

        SnowflakeShardingKeyGenerator shardingKeyGenerator = new SnowflakeShardingKeyGenerator();

        BlockingDeque<Long> deque = new LinkedBlockingDeque<>();
        List<Consumer> consumerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            consumerList.add(new Consumer(deque, 10_000_000));
        }

        for (int i = 0; i < 10; i++) {
            cyclier.submit(consumerList.get(i));
        }

        for (int i = 0; i < 3; i++) {
            generetor.submit(new GeneNum(shardingKeyGenerator, deque));
        }

//        boolean termination = generetor.awaitTermination(5, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(6);

        generetor.shutdownNow();

        while (!deque.isEmpty()) {
            if (deque.isEmpty()) {
                cyclier.shutdownNow();
                break;
            }
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(deque.size());
        }
        System.out.println("  ========== computation ======= ");
        consumerList.stream().forEach((c) -> {
            List<Long> list = c.getList().stream().sorted().collect(Collectors.toList());
            System.out.println(list.size());
        });

        System.out.println(" ======= ");
        TimeUnit.MINUTES.sleep(5);
    }

    static class Consumer implements Runnable {

        BlockingDeque<Long> deque;

        private volatile List<Long> list;

        public Consumer(BlockingDeque<Long> deque, int size) {
            this.deque = deque;
            list = new ArrayList<>(size);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    list.add(deque.take());
                } catch (InterruptedException e) {
                    System.out.println("exit Consumer Thread : " + Thread.currentThread().getName());
                    break;
                }
            }
        }

        public List<Long> getList() {
            return list;
        }

    }

    static class GeneNum implements Runnable {
        SnowflakeShardingKeyGenerator shardingKeyGenerator;

        BlockingQueue<Long> deque;

        public GeneNum(SnowflakeShardingKeyGenerator shardingKeyGenerator, BlockingQueue<Long> deque) {
            this.shardingKeyGenerator = shardingKeyGenerator;
            this.deque = deque;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    deque.put((Long) shardingKeyGenerator.generateKey());
                } catch (InterruptedException e) {
                    System.out.println("exit GeneNum Thread : " + Thread.currentThread().getName());
                    break;
                }
            }
        }
    }
}
