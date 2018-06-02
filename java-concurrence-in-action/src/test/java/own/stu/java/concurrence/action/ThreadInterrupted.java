package own.stu.java.concurrence.action;

import java.util.concurrent.TimeUnit;

/**
 * Created by CHANEL on 2018/5/27.
 */
public class ThreadInterrupted {

    public static void main(String[] args) {
        Busi busi = new Busi();
        busi.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        busi.interrupt();
    }

    static class Busi extends Thread {
        @Override
        public void run() {
            System.out.println("start .... ");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("interrupted .... ");
            }
            System.out.println("stop ..... ");
        }
    }
}
