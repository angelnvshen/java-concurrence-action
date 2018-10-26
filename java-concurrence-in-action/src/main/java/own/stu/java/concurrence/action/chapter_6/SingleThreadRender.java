package own.stu.java.concurrence.action.chapter_6;

import java.util.ArrayList;
import java.util.List;

public abstract class SingleThreadRender implements BaseRender {

  void renderPage(CharSequence s){
    renderText(s);
    List<ImageData> imageData = new ArrayList<>();
    for(ImageInfo imageInfo : scanForImageInfo(s)){
      imageData.add(imageInfo.downloadImage());
    }

    for(ImageData data : imageData) {
      renderImage(data);
    }
  }

}
