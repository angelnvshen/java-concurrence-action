package own.bytecode;

import com.google.common.base.Strings;

public class ByteCodeDemo {
    private int a = 1;

    public int add() {
        int b = 2;
        int c = a + b;
        System.out.println(c);
        return c;
    }

    public static void main(String[] args) {
        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY = (1 << COUNT_BITS) - 1;

        int RUNNING = -1 << COUNT_BITS;
        int SHUTDOWN = 0 << COUNT_BITS;
        int STOP = 1 << COUNT_BITS;
        int TIDYING = 2 << COUNT_BITS;
        int TERMINATED = 3 << COUNT_BITS;
        System.out.println(toBinaryString(CAPACITY));

        System.out.println(toBinaryString(RUNNING));
        System.out.println(toBinaryString(SHUTDOWN));
        System.out.println();
        System.out.println(toBinaryString(STOP));

        System.out.println(toBinaryString(TIDYING));
        System.out.println(toBinaryString(TERMINATED));
    }

    private static String toBinaryString(int n) {
        String binary = Integer.toBinaryString(n);
        if (binary.length() >= 32) return binary;

        return Strings.padStart(binary, 32 , '0');
    }
}
