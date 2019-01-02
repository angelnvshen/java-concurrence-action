package own.stu.sourcecore.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class Client {

  public static void nioClient() {

    ByteBuffer buffer = ByteBuffer.allocate(1024);
    SocketChannel socketChannel = null;
    try {
      socketChannel = SocketChannel.open();
      socketChannel.configureBlocking(false);
      socketChannel.connect(new InetSocketAddress("127.0.0.1", 7070));
      if (socketChannel.finishConnect()) {
        int i = 0;
        while (true) {
          TimeUnit.SECONDS.sleep(10);
          String info = "I'm " + i++ + "-th information from client";
          buffer.clear();
          buffer.put(info.getBytes());
          buffer.flip();
//          while (buffer.hasRemaining()) {
            System.out.println(info);
            socketChannel.write(buffer);
//          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      try {
        if (socketChannel != null) {
          socketChannel.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
