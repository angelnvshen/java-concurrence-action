package own.stu.sourcecore.java.lock.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

  private AtomicInteger counter = new AtomicInteger();

  public int get(){
    return counter.get();
  }

  public void increase(){
    counter.getAndIncrement();
  }
}
