package own.jdk.coure.forFun;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageTest {

    @Test
    public void test1() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new URL("https://pic3.zhimg.com/80/v2-c8df502eb0242592ac41ccbc0264265a_hd.jpg"));
        Color color = new Color(bufferedImage.getRGB(0, 0));
        System.out.println(color);
    }

    private static void putPixel(BufferedImage image, int widthStart, int height, int rgb) {
        for (int h = 0; h < height; ++h)
            for (int i = 0; i < 30; i++) image.setRGB(widthStart + i, h, rgb);
    }

    @Test
    public void test2() throws IOException {

        int width = 30, height = 30;
        BufferedImage img = new BufferedImage(width * 10, height, BufferedImage.TYPE_INT_RGB);
        int r = 255, g = 0, b = 0;
        for (int i = 0; i < 10; i++) {
            putPixel(img, i * 30, height, new Color(r - i, g, b).getRGB());
        }
        ImageIO.write(img, "png", new File("tmp.png"));
    }

    @Test
    public void test() throws IOException {
        BufferedImage secretImg = ImageIO.read(new URL("https://pic3.zhimg.com/80/v2-c8df502eb0242592ac41ccbc0264265a_hd.jpg"));
        int width = secretImg.getWidth(), height = secretImg.getHeight();
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                Color color = new Color(secretImg.getRGB(w, h));
                int lsb = color.getRed() % 2; // 个人建议把 % 2 写成 & 1
                secretImg.setRGB(w, h, (lsb == 0 ? new Color(0, 0, 0) : new Color(255, 255, 255)).getRGB());
            }
        }
        ImageIO.write(secretImg, "png", new File("secret.png"));
    }
}
