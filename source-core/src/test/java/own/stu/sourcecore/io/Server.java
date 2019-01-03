package own.stu.sourcecore.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {

  private static int port = 7070;
  private static final int BUF_SIZE=1024;
  
  public static void ioServer() {

    ServerSocket serverSocket = null;
    InputStream inputStream = null;
    try {
      serverSocket = new ServerSocket(port);

      int receiveMsgSize = 0;
      byte[] receiveBuf = new byte[1024];

      while (true) {
        Socket clientSocket = serverSocket.accept();
        SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();
        System.out.println("Handling client at " + clientAddress);
        inputStream = clientSocket.getInputStream();

        while ((receiveMsgSize = inputStream.read(receiveBuf)) != -1) {
          byte[] temp = new byte[receiveMsgSize];
          System.arraycopy(receiveBuf, 0, temp, 0, receiveMsgSize);
          System.out.println(new String(temp));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {

      try {
        if (serverSocket != null) {
          serverSocket.close();
        }
        if (inputStream != null) {
          inputStream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void nioServer() {

    try (
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open()){

      serverSocketChannel.socket().bind(new InetSocketAddress(port));
      serverSocketChannel.configureBlocking(false);
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
      while (true){
        if(selector.select(3000) == 0){
          System.out.println(" ==== ");
          continue;
        }
        Iterator<SelectionKey> selectKeys = selector.selectedKeys().iterator();
        while (selectKeys.hasNext()){
          SelectionKey key = selectKeys.next();
          if(key.isAcceptable()){
            handleAccept(key);
          }
          if(key.isWritable() && key.isValid()){
            handleWrite(key);
          }
          if(key.isReadable()){
            handleRead(key);
          }
          if(key.isConnectable()){
            System.out.println("isConnected = true");
          }
          selectKeys.remove();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void handleRead(SelectionKey key) throws IOException {
    SocketChannel socketChannel = (SocketChannel) key.channel();
    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
    long bytesRead = socketChannel.read(byteBuffer);
    while(bytesRead>0){
      byteBuffer.flip();
      while(byteBuffer.hasRemaining()){
        System.out.print((char)byteBuffer.get());
      }
      System.out.println();
      byteBuffer.clear();
      bytesRead = socketChannel.read(byteBuffer);
    }
    if(bytesRead == -1){
      socketChannel.close();
    }
  }

  private static void handleWrite(SelectionKey key) throws IOException{
    ByteBuffer buf = (ByteBuffer)key.attachment();
    buf.flip();
    SocketChannel sc = (SocketChannel) key.channel();
    while(buf.hasRemaining()){
      sc.write(buf);
    }
    buf.compact();
  }

  private static void handleAccept(SelectionKey key) throws IOException {
    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
    SocketChannel socketChannel = serverSocketChannel.accept();
    socketChannel.configureBlocking(false);
    socketChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
  }
}
