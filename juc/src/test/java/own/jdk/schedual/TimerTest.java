package own.jdk.schedual;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

import static own.jdk.executorService.scheduled.TimerExample.*;

public class TimerTest {

    @Test
    public void testSchedule_One_Task() throws InterruptedException {

        Timer timer = new Timer();
        printNow();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(" ======= " + now());
            }
        }, 3000);

        Thread.currentThread().join();
    }

    @Test
    public void testSchedule_Multi_Task() throws InterruptedException {

        Timer timer = new Timer();
        printNow();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(" ======= " + now());
            }
        }, 3000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(" ======= " + now());
            }
        }, 5000);

        Thread.currentThread().join();
    }

    @Test
    public void testSchedule_FixDelay_Task() throws InterruptedException {

        Timer timer = new Timer();
        printNow();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sleep(3);
                System.out.println(Thread.currentThread().getName() + " ======= " + now());
            }
        }, 3000, 2000);

        /*timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " ======= " + now());
            }
        }, 5000);*/

        Thread.currentThread().join();
    }

    @Test
    public void testSchedule_FixRate_Task() throws InterruptedException {

        Timer timer = new Timer();
        printNow();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sleep(3);
                System.out.println(Thread.currentThread().getName() + " ======= " + now());
            }
        }, 3000, 2000);

        Thread.currentThread().join();
    }

    @Test
    public void testSchedule_Task_Queue_Empty() throws InterruptedException {

        Timer timer = new Timer();
        printNow();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " ======= " + now());
            }
        }, 1000);
        sleep(2);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " ======= " + now());
            }
        }, 1000);

        Thread.currentThread().join();
    }

}
