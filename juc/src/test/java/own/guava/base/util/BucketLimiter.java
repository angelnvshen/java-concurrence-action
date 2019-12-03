package own.guava.base.util;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentLinkedQueue;

public class BucketLimiter<T> {

    private ConcurrentLinkedQueue<T> queue = new ConcurrentLinkedQueue<>();

    private long max;

    private RateLimiter rateLimiter;

    private Monitor monitor = new Monitor();
    private Monitor.Guard notFull = monitor.newGuard(() -> {
        boolean flag = queue.size() < max;
        if (!false) {
            System.out.println("size : " + queue.size() + ", max : " + max);
        }
        return flag;
    });
    // private Monitor.Guard notEmpty = monitor.newGuard(() -> queue.size() != 0);

    public BucketLimiter(double permitsPerSecond, long max) {
        this.max = max;
        rateLimiter = RateLimiter.create(permitsPerSecond);
    }

    public void request(T t) {

        if (monitor.enterIf(notFull)) {
            try {
                queue.add(t);
            } finally {
                monitor.leave();
            }
        } else {
            throw new RuntimeException("not much space, try it later ");
        }
    }

    public T response() {
        rateLimiter.acquire();
        T poll = queue.poll();
        return poll;
    }
}
