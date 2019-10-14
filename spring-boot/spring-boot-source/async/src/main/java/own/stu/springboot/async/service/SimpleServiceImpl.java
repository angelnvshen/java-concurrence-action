package own.stu.springboot.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SimpleServiceImpl {

    @Async
    public void test() {
        System.out.println("simple service without interface");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("simple service finish");
    }
}
