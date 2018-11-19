package own.stu.tkmybatis.demo.controller;

import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.tkmybatis.demo.controller.async.AsyncTask;
import own.stu.tkmybatis.demo.controller.async.AsyncTaskWithResult;

@RestController
@RequestMapping("async")
public class AsyncTaskController {

  @Autowired
  private AsyncTask asyncTask;

  @Autowired
  private AsyncTaskWithResult taskWithResult;

  @RequestMapping("test1")
  public String doTask() throws InterruptedException{
    long currentTimeMillis = System.currentTimeMillis();
    this.task1();
    this.task2();
    this.task3();
    long currentTimeMillis1 = System.currentTimeMillis();
    return "task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms";
  }

  @RequestMapping("test2")
  public String doTask2() throws InterruptedException{
    long currentTimeMillis = System.currentTimeMillis();
    asyncTask.task1();
    asyncTask.task2();
    asyncTask.task3();
    long currentTimeMillis1 = System.currentTimeMillis();
    return "task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms";
  }

  @RequestMapping("test3")
  public String doTask3() throws InterruptedException{
    long currentTimeMillis = System.currentTimeMillis();
    Future<String> result1 = taskWithResult.task1();
    Future<String> result2 = taskWithResult.task2();
    Future<String> result3 = taskWithResult.task3();
    while (true){
      if(result1.isDone() && result2.isDone() && result3.isDone())
        break;
    }
    long currentTimeMillis1 = System.currentTimeMillis();
    return "task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms";
  }

  @Async
  public void task1() throws InterruptedException{
    long currentTimeMillis = System.currentTimeMillis();
    Thread.sleep(1000);
    long currentTimeMillis1 = System.currentTimeMillis();
    System.out.println("task1任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
  }

  @Async
  public void task2() throws InterruptedException{
    long currentTimeMillis = System.currentTimeMillis();
    Thread.sleep(2000);
    long currentTimeMillis1 = System.currentTimeMillis();
    System.out.println("task2任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
  }

  @Async
  public void task3() throws InterruptedException {
    long currentTimeMillis = System.currentTimeMillis();
    Thread.sleep(3000);
    long currentTimeMillis1 = System.currentTimeMillis();
    System.out.println("task3任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
  }
}
