package own.jdk.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferDemo {
    public static void main(String[] args) throws IOException {
        String filePath = "/Users/my/IdeaProjects_own/core/studyrecord/src/main/webapp/static/java/core/Collection.md";
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");

        FileChannel fileChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        int read = fileChannel.read(byteBuffer);
        while (read != -1) {
            byteBuffer.flip();
            if (byteBuffer.hasRemaining())
                System.out.println(byteBuffer.get()); // 循环读取时注意，position 和 limit的值 ，所有需要if(byteBuffer.hasRemaining()) ....
            byteBuffer.clear();
            read = fileChannel.read(byteBuffer);
        }
        file.close();
    }

    private void appendToFile(ByteBuffer buffer){

    }
}
