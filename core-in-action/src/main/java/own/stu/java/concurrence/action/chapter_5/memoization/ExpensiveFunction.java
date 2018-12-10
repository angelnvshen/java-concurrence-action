package own.stu.java.concurrence.action.chapter_5.memoization;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ExpensiveFunction implements Computable<String, BigInteger>{
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        //长计算
        TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        return new BigInteger(arg);
    }
}
