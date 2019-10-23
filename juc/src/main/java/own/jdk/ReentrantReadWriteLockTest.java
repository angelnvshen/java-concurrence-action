package own.jdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock r = rwl.readLock();
    ReentrantReadWriteLock.WriteLock w = rwl.writeLock();

    private Map<String, Object> map = new HashMap<>(256);

    public void add(String key, Object value) {
        w.lock();
        try {
            map.putIfAbsent(key, value);
        } finally {
            w.unlock();
        }
    }

    public Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
        List<Thread> threads = new ArrayList<>();

        for (int t = 0; t < 10; t++) {
            Thread thread = new Thread(() -> {
                for (int i = 0; i < 240; i++) {
                    test.add(String.valueOf(i), String.format("%d-value", i));
                }
            });
            threads.add(thread);
        }
        threads.forEach(thread -> {
            try {
                thread.start();
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //TimeUnit.SECONDS.sleep(5);
        for (int i = 0; i < 240; i++) {
            System.out.println(i + " -> " + test.get(String.valueOf(i)));
        }

    }
}
