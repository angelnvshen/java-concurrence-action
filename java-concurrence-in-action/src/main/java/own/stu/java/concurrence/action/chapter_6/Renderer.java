package own.stu.java.concurrence.action.chapter_6;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public abstract class Renderer implements BaseRender {

  private final ExecutorService executorService;

  public Renderer(ExecutorService executorService) {
    this.executorService = executorService;
  }

  void renderPage(CharSequence s){
    List<ImageInfo>  imageInfos = scanForImageInfo(s);

    CompletionService<ImageData> completionService =
        new ExecutorCompletionService<>(executorService);

    for(final ImageInfo imageInfo : imageInfos){
      completionService.submit(new Callable<ImageData>() {
        @Override
        public ImageData call() throws Exception {
          return imageInfo.downloadImage();
        }
      });
    }

    renderText(s);

      try {
        for(int i = 0; i<imageInfos.size(); i++){
          Future<ImageData> f = completionService.take();
          ImageData imageData = f.get();
          renderImage(imageData);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
  }

}
