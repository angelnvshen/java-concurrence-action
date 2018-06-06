package own.stu.algorithms_4th.fundamentals.basicProgrammingModel.question;

import org.junit.Test;

public class TestQ {

    @Test
    public void test() {
//        double i = (1 + 2.236)/2;
//        double i = 1 + 2 + 3 + 4.0;
//        boolean i = 4.1 >= 4;
//        String i = 1 + 2 + "3";
//        System.out.println(getType(i) + " - " + i);
//        double x = 0.01, y =0.01;
//        if(0<x && x<1.0 &&  0<y && y<1.0){
//            System.out.println("TRUE");
//        }

       /* int f = 0;
        int g = 1;
        for (int i = 0; i <= 15; i++) {
            System.out.println(f + " - " + g);
            f = f + g;
            g = f - g;
        }*/

       /* double t = 9.0;
        while (Math.abs(t - 9.0/t) > .001)
            t = (9.0/t + t) / 2.0;
        System.out.printf("%.5f\n", t);*/

//        System.out.println('b');
//        System.out.println('b' + 'c');
//        System.out.println((char) ('a' + 4));

       /* int[] a = new int[10];
        for (int i = 0; i < 10; i++)
            System.out.print(a[i] + " ");
        System.out.println();

        for (int i = 0; i < 10; i++)
            a[i] = 9 - i;

        for (int i = 0; i < 10; i++)
            a[i] = a[a[i]];

        for (int i = 0; i < 10; i++)
            System.out.print(a[i] + " ");*/

       /* int[][] arr = new int[3][4];
        System.out.println(arr.length);
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[i].length; j++)
                arr[i][j] = i + j + 2;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }*/

//        System.out.println(lg(4));
//        System.out.println(exR1(6));
//        System.out.println(mystery(2, 25));
//        System.out.println(mystery(3, 11));
//        System.out.println(mystery2(2, 4));
//        System.out.println(mystery2(3, 2));

        int i = 0;
        System.out.println(i);
        System.out.println(i++);
        System.out.println(i);
    }

    public static int mystery(int a, int b) {
        if (b == 0) return 0;
        if (b % 2 == 0) return mystery(a + a, b / 2);
        return mystery(a + a, b / 2) + a;
    }

    public static int mystery2(int a, int b) {
        if (b == 0) return 1;
        if (b % 2 == 0) return mystery(a + a, b / 2);
        return mystery(a + a, b / 2) * a;
    }

    public static String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }

    private int lg(int num) {
        int now = 0;
        for (int i = 1; i < num; i *= 2)
            now++;
        return now - 1;
    }

    //将正整数N的二进制表示形式放入String中
    private String positiveIntegerToBinary(int num) {
        String s = "";
        int n = 11;
        for (int i = n; i > 0; i /= 2)
            s = (i % 2) + s;
        return s;
    }

    // 简答的判断数据类型
    private <T> String getType(T b) {

        if (b instanceof Double)
            return "double";
        if (b instanceof Float) {
            return "Float";
        }
        if (b instanceof Integer)
            return "Integer";
        if (b instanceof Character)
            return "Character";
        if (b instanceof Byte)
            return "Byte";
        if (b instanceof Boolean)
            return "Boolean";

        if (b instanceof Object)
            return "Object";

        return "-----";
    }
}
