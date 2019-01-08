package own.stu.sourcecore.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import org.junit.Test;

public class IOTest {

  @Test
  public void test(){
    String file = "/Users/ScorpionKing/Desktop/sql-.txt";

    try (RandomAccessFile accessFile = new RandomAccessFile(file, "rw")) {
      FileChannel fileChannel = accessFile.getChannel();
      ByteBuffer byteBuffer = ByteBuffer.allocate(11);


    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testByteBuffer(){
    ByteBuffer byteBuffer = ByteBuffer.allocate(11);

    printByteBuffer(byteBuffer);

    byteBuffer.put("123".getBytes());

    printByteBuffer(byteBuffer);

    byteBuffer.flip();

    System.out.println(String.valueOf(byteBuffer.get()));
  }

  private void printByteBuffer(ByteBuffer byteBuffer) {
    System.out.println(
        "capacity: " + byteBuffer.capacity() + ", " +
        "limit: " + byteBuffer.limit() + ", " +
        "position: " + byteBuffer.position() + ", " +
        "mark: " + byteBuffer.mark());
  }

  @Test
  public void testServer(){
//    Server.ioServer();
    Server.nioServer();
  }

  @Test
  public void testClient(){
    Client.nioClient();
  }

  @Test
  public void test4(){
    System.out.println(SelectionKey.OP_ACCEPT);
    System.out.println(SelectionKey.OP_CONNECT);
    System.out.println(SelectionKey.OP_READ);
    System.out.println(SelectionKey.OP_WRITE);
  }
}