package own.stu.java.concurrence.action.chapter_7;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class LogWriter {

  private BlockingQueue<String> queue;
  private LoggerThread logger;
  private static final int CAPACITY = 1000;

  public LogWriter(Writer writer) {
    this.queue = new LinkedBlockingDeque<>(CAPACITY);
    this.logger = new LoggerThread(writer);
  }

  public void start(){
    logger.start();
  }

  public void log(String message) throws InterruptedException {
    queue.put(message);
  }

  private class LoggerThread extends Thread{
    private final PrintWriter writer;

    LoggerThread(Writer writer){
      this.writer = new PrintWriter(writer, true);
    }

    @Override
    public void run() {

      try {
        while (true) {
          writer.println(queue.take());
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        writer.close();
      }
    }
  }

  public static void main(String[] args) {
    LogWriter writer = new LogWriter(new StringWriter());
    writer.start();
    ExecutorService service = Executors.newFixedThreadPool(4);
    service.submit(()->{
      try {
        writer.log("---------" + Math.random());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }

}
