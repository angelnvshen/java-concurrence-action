package own.guava.base.enven.listener;

import com.google.common.eventbus.Subscribe;

import static own.jdk.executorService.scheduled.TimerExample.sleep;


public class HelloListener {

    @Subscribe
    public void test(String type) {
        sleep(2);
        System.out.println(this.getClass().getName() + " -> test -> " + type);
    }

    @Subscribe
    public void test2(String type) {
        sleep(2);
        System.out.println(this.getClass().getName() + " -> test2 -> " + type);
    }

    @Subscribe
    public void test3(Integer value) {
        System.out.println(this.getClass().getName() + " -> test3 -> " + value);
    }
}
