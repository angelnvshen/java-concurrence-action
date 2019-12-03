package own.guava.base.util;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static own.jdk.executorService.scheduled.TimerExample.sleep;

public class TokenLimiter {
    private long max;

    private RateLimiter rateLimiter;

    AtomicLong increment = new AtomicLong(1);

    public TokenLimiter(double permitsPerSecond, long max) {
        this.max = max;
        rateLimiter = RateLimiter.create(permitsPerSecond);
    }

    // TODO not right thread-safe here
    public void request() {
        if (rateLimiter.tryAcquire(5, TimeUnit.SECONDS)) {

            if (increment.get() > max) {
                throw new RuntimeException(Thread.currentThread().getName() + " sale all out , meet you next time , bye ... ");
            }
            long ticket = increment.getAndIncrement();
            handler(ticket);
        } else {
            throw new RuntimeException(Thread.currentThread().getName() + " too much request, try it again!");
        }
    }

    private void handler(long ticket) {
        sleep(20, TimeUnit.MILLISECONDS);
        System.out.println(Thread.currentThread().getName() + " get ticket " + ticket);
    }
}
