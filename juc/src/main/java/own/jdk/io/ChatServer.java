package own.jdk.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ChatServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8070));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0)
                continue;

            Iterator<SelectionKey> selectionKeysIterator = selector.selectedKeys().iterator();

            while (selectionKeysIterator.hasNext()) {
                SelectionKey key = selectionKeysIterator.next();
                if (key.isValid() && key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);
                }

                if (key.isValid() && key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                    while (true) {
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);
                        if (read <= 0)
                            break;

                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                    }

                }
                selectionKeysIterator.remove();
            }

        }
    }
}
