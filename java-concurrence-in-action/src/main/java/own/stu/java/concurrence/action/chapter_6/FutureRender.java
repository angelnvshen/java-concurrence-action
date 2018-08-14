package own.stu.java.concurrence.action.chapter_6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class FutureRender implements BaseRender {

  final private ExecutorService executorService = Executors.newFixedThreadPool(10);

  void renderPage(CharSequence s){
    final List<ImageInfo> imageInfos = scanForImageInfo(s);

    Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
      @Override
      public List<ImageData> call() {
        List<ImageData> result = new ArrayList<>();
        for(ImageInfo imageInfo : imageInfos){
          result.add(imageInfo.downloadImage());
        }
        return result;
      }
    };

    Future<List<ImageData>> future = executorService.submit(task);
    renderText(s);

    try {
      List<ImageData> imageData = future.get();
      for(ImageData data : imageData)
        renderImage(data);

    } catch (InterruptedException e) {
      e.printStackTrace();
      future.cancel(true);
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

}
