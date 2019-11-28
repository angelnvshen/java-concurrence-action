package own.jdk.queue;

import java.io.Closeable;
import java.io.IOException;

public class QueueTest {
    public static void main(String[] args) throws IOException {

        OpImpl op = new OpImpl();
        Closeable closeable = () -> op.read(); // 技巧：可以让使用者屏蔽接口的一部分功能
        closeable.close();
    }

    static class OpImpl implements Op {

        @Override
        public void read() {
            System.out.println(" ============= ");
        }

        @Override
        public void write(String value) {
            System.out.println(" +++++++++++++ " + value);
        }
    }

    interface Op {
        void read();

        void write(String value);
    }
}
