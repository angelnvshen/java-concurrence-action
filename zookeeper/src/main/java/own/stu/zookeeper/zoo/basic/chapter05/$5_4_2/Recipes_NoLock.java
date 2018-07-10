package own.stu.zookeeper.zoo.basic.chapter05.$5_4_2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class Recipes_NoLock {
    public static void main(String[] args) {
        final CountDownLatch count = new CountDownLatch(1);
        for(int i=0;i<10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        count.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = format.format(new Date());
                    System.err.println("order is : " + orderNo);
                }
            }).start();

            count.countDown();
        }
    }
}
