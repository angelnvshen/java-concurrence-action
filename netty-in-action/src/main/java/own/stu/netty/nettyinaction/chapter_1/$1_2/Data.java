package own.stu.netty.nettyinaction.chapter_1.$1_2;

/**
 * Created by CHANEL on 2018/7/14.
 */
public class Data {
    private int n;
    private int m;

    public Data(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public String toString(){
        int r = n/m;
        return n + "/" + m + " = " + r;
    }
}
