package own.stu.algorithms_4th.string;

import org.junit.Test;

/**
 * Created by CHANEL on 2019/5/18.
 */
public class CharTest {

    @Test
    public void test(){
        System.out.println(lgR(17));
        System.out.println(lgR(15));
    }

    public int lgR(int R) {
        int lgR = 0;
        for (int r = R; r > 0; r = r / 2) {
            lgR ++;
        }
        return lgR;
    }
}
