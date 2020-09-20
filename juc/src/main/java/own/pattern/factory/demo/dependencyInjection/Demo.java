package own.pattern.factory.demo.dependencyInjection;

import com.google.common.util.concurrent.RateLimiter;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        RateLimiter rateLimiter = (RateLimiter) applicationContext.getBean("rateLimiter");
        rateLimiter.toString();
        //...
    }
}