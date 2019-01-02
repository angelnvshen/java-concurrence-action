package own.stu.sourcecore.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Server {

  public static void ioServer() {

    ServerSocket serverSocket = null;
    InputStream inputStream = null;
    try {
      serverSocket = new ServerSocket(7070);

      int receiveMsgSize = 0;
      byte[] receiveBuf = new byte[1024];

      while (true) {
        Socket clientSocket = serverSocket.accept();
        SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();
        System.out.println("Handling client at " + clientAddress);
        inputStream = clientSocket.getInputStream();

        while ((receiveMsgSize = inputStream.read()) > -1) {
          byte[] temp = new byte[receiveMsgSize];
          System.arraycopy(receiveBuf, 0, temp, 0, receiveMsgSize);
          System.out.println(new String(temp));
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

}
