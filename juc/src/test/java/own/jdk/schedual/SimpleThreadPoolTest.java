package own.jdk.schedual;

import org.junit.Test;
import org.quartz.SchedulerConfigException;
import org.quartz.simpl.SimpleThreadPool;
import own.jdk.common.TestCommon;

public class SimpleThreadPoolTest extends TestCommon {

    @Test
    public void test_normal() throws SchedulerConfigException {

        SimpleThreadPool simpleThreadPool = new SimpleThreadPool();
        simpleThreadPool.setThreadCount(3);

        simpleThreadPool.initialize();

        simpleThreadPool.runInThread(() -> System.out.println("========"));
    }

    @Test
    public void test_exception_happen() {

        SimpleThreadPool simpleThreadPool = initThreadPool(1);
        simpleThreadPool.runInThread(() -> {
            int i = 100 / 0;
            i++;
        });

        simpleThreadPool.runInThread(() -> System.out.println("========"));
    }

    public SimpleThreadPool initThreadPool(int threadCounts) {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool();
        simpleThreadPool.setThreadCount(threadCounts);
        simpleThreadPool.setThreadNamePrefix("YIN");
        try {
            simpleThreadPool.initialize();
        } catch (SchedulerConfigException e) {
            e.printStackTrace();
        }
        return simpleThreadPool;
    }


}
