package own.stu.java.concurrence.action.chapter_6;

import java.util.List;

public interface BaseRender {
  interface ImageData {
  }

  interface ImageInfo {
    SingleThreadRender.ImageData downloadImage();
  }

  void renderText(CharSequence s);

  List<SingleThreadRender.ImageInfo> scanForImageInfo(CharSequence s);

  void renderImage(SingleThreadRender.ImageData i);

}
