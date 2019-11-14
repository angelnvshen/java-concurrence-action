package own.stu.redis.oneMaster;

import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.util.SafeEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;

/**
 * REsp is redis serialization protocol
 * <p>
 * In RESP, the type of some data depends on the first byte:
 * <p>
 * For Simple Strings the first byte of the reply is "+"
 * For Errors the first byte of the reply is "-"
 * For Integers the first byte of the reply is ":"
 * For Bulk Strings the first byte of the reply is "$"
 * For Arrays the first byte of the reply is "*"
 * Additionally RESP is able to represent a Null value using a special variation of Bulk Strings or Array as specified later.
 * <p>
 * In RESP different parts of the protocol are always terminated with "\r\n" (CRLF).
 */
public class REspTest {

    @Test
    public void testJedisClient() {

        Jedis jedis = new Jedis("127.0.0.1", 6179, 60000);
        jedis.del("string79897");
    }

    @Test
    public void testFakeServer() throws IOException {
        ServerSocket socket = new ServerSocket(6179);
        Socket accept = socket.accept();

        byte[] bytes = new byte[1024];
        accept.getInputStream().read(bytes);
        System.out.println(new String(bytes));

        /*ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(6179));

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        Socket accept = serverSocket.accept();
        accept.getChannel().read(byteBuffer);
        System.out.println(byteBuffer.toString());*/
    }

    @Test
    public void teustomizedClient() throws IOException {
        Client client = new Client("localhost", 6379);
//        client.set("string", "hello-custom-2");
//        System.out.println(client.get("string"));
        for (int i = 0; i < 100000; i++) {
            client.del("string" + i);
//            client.set("string" + i, "hello-custom-2" + i);
//            System.out.println(client.get("string") + i);
        }
    }

    static class Client {

        Connection connection;

        public Client(String host, int port) {
            connection = new Connection(host, port);
        }

        public void set(String key, String value) {
            connection.sendCommand(Command.SET, key.getBytes(), value.getBytes());
        }

        public String get(String string) {
            connection.sendCommand(Command.GET, string.getBytes());
            return new String(connection.getData());
        }

        public void del(String... keys) {
            connection.sendCommand(Command.DEL, SafeEncoder.encodeMany(keys));
            System.out.println(new String(connection.getData()));
        }
    }

    static class Connection {
        String host;
        int port;
        OutputStream outputStream;
        InputStream inputStream;
        Socket socket;

        public Connection(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public void connection() {
            try {
                if (isConnected()) {
                    return;
                }
                socket = new Socket(host, port);
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public boolean isConnected() {
            return this.socket != null && this.socket.isBound() && !this.socket.isClosed()
                    && this.socket.isConnected() && !this.socket.isInputShutdown() && !this.socket.isOutputShutdown();
        }

        public void sendCommand(Command command, byte[]... args) {

            connection();

            Protocol.sendCommand(outputStream, command, args);
        }

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        public byte[] getData() {

            try {
                outStream.reset();
                int len = inputStream.read(buffer);
                outStream.write(buffer, 0, len);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return outStream.toByteArray();


            /*

            block .... not right

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];

            int len = -1;
            while (true) {
                try {
                    if (!((len = inputStream.read(buffer)) != -1)) break;
                    outStream.write(buffer, 0, len);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return outStream.toByteArray();*/

            /*byte[] bytes = new byte[1024];
            try {
                inputStream.read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bytes;*/
        }
    }

    static class Protocol {

        private static String star = "*";
        private static String doller = "$";
        private static String blank = "\r\n";

        public static void sendCommand(OutputStream outputStream, Command command, byte[]... args) {

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(star).append(args.length + 1).append(blank); // *2 数组 长度2
            stringBuffer.append(doller).append(command.name().length()).append(blank); // $3
            stringBuffer.append(command.name()).append(blank); // GET
            for (byte[] bytes : args) {
                String s = new String(bytes);
                stringBuffer.append(doller).append(s.length()).append(blank); // $3
                stringBuffer.append(s).append(blank); // GET
            }
            try {
                outputStream.write(stringBuffer.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    enum Command {
        SET, GET, DEL
    }

    @Test
    public void testSendCommand() {
        Protocol.sendCommand(null, Command.GET, "string".getBytes());
        System.out.println(" ========== ");
        Protocol.sendCommand(null, Command.SET, "string".getBytes(), "hello".getBytes());
    }
}
