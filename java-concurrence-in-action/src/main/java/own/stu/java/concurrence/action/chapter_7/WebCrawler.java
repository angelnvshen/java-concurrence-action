package own.stu.java.concurrence.action.chapter_7;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class WebCrawler {

  private TrackingExecutor executor;

  private Set<URL> urlsToCrawl = new HashSet<>();

  private final ConcurrentHashMap<URL, Boolean> seen = new ConcurrentHashMap<>();

  private static final long TIMEOUT = 500;

  private static final TimeUnit UNIT = MILLISECONDS;

  public WebCrawler(URL startUrl){
    urlsToCrawl.add(startUrl);
  }

  public synchronized void start(){
    executor = new TrackingExecutor(Executors.newCachedThreadPool());
    for(URL url : urlsToCrawl){
      submitCrawlTask(url);
    }
  }

  public synchronized void stop() throws InterruptedException {
    saveUnCrawled(executor.shutdownNow());
    if(executor.awaitTermination(TIMEOUT, UNIT)){
      saveUnCrawled(executor.getCancelledTasks());
    }
  }

  public void saveUnCrawled(List<Runnable> unCrawled){
    for(Runnable runnable : unCrawled){
      urlsToCrawl.add(((CrawlTask)runnable).getPage());
    }
  }

  private void submitCrawlTask(URL url) {
    executor.execute(new CrawlTask(url));
  }

  protected abstract List<URL> processPage(URL url);

  private class CrawlTask implements Runnable{

    private URL url;

    CrawlTask(URL url) {
      this.url = url;
    }

    boolean alreadyCrawled(){
      return seen.put(url, true) != null;
    }

    void markUnCrawled(){
      seen.remove(url);
      System.out.printf("marking %s uncrawled%n", url);
    }

    @Override
    public void run() {
      for(URL url : processPage(url)){
        if(Thread.currentThread().isInterrupted())
          return;

        submitCrawlTask(url);
      }
    }

    public URL getPage(){
      return url;
    }

  }
}
