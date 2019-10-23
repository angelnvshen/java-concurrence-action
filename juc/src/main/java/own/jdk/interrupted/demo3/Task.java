package own.jdk.interrupted.demo3;

import java.util.concurrent.Callable;

/**
 * @ClassName: Task
 */
public class Task implements Callable<String> {

    //创建Task类，指定实现Callable接口，并参数化为String类型。
    //实现call()方法，写入一条信息到控制台，并使这个线程在循环中睡眠100毫秒。
    @Override
    public String call() throws Exception {
        while (true) {
            System.out.println("我在执行任务: Test 来自" + Thread.currentThread().getName() + "\n");
            Thread.sleep(100);
        }
    }
}