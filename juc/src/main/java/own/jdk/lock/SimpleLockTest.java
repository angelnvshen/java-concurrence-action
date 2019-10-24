package own.jdk.lock;

import sun.misc.Lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleLockTest {

    final Lock lock = new Lock();

    List<String> list = new ArrayList<>();

    public void add(String value) {
        try {
            lock.lock();
            list.add(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String remove() {
        try {
            lock.lock();
            return list.remove(list.size() - 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        SimpleLockTest list = new SimpleLockTest();
        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                list.add(i + "");
            }
        });

        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(list.remove());
            }
        });

        executorService.awaitTermination(1, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(list.list.size());
    }
}
