package own.guava.base.enven;

import com.google.common.eventbus.AsyncEventBus;
import org.junit.After;
import org.junit.Test;
import own.guava.base.enven.listener.HelloListener;

import java.util.concurrent.Executors;

import static own.jdk.executorService.CompletionFutureTest.sleep;

public class EventBusTest {

    @Test
    public void test() {

//        EventBus eventBus = new EventBus();
        AsyncEventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(3));
        eventBus.register(new HelloListener());

        eventBus.post("12月了");
        eventBus.post(2);
    }

    @After
    public void waitMoment() {
        sleep(5000);
    }
}
