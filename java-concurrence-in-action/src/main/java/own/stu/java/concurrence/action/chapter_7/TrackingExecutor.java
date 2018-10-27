package own.stu.java.concurrence.action.chapter_7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TrackingExecutor extends AbstractExecutorService{

  private final ExecutorService executorService;

  private final Set<Runnable> tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<>());

  public TrackingExecutor(ExecutorService executorService) {
    this.executorService = executorService;
  }

  public List<Runnable> getCancelledTasks(){
    if(!executorService.isTerminated()){
      throw new IllegalThreadStateException("无效的状态");
    }
    return new ArrayList<>(tasksCancelledAtShutdown);
  }

  @Override
  public void shutdown() {
    executorService.shutdown();
  }

  @Override
  public List<Runnable> shutdownNow() {
    return executorService.shutdownNow();
  }

  @Override
  public boolean isShutdown() {
    return executorService.isShutdown();
  }

  @Override
  public boolean isTerminated() {
    return executorService.isTerminated();
  }

  @Override
  public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
    return executorService.awaitTermination(timeout, unit);
  }

  @Override
  public void execute(Runnable command) {
    executorService.execute(() -> {
      try {
        command.run();
      }finally {
        if(isShutdown() && Thread.currentThread().isInterrupted())
          tasksCancelledAtShutdown.add(command);
      }
    });
  }
}
