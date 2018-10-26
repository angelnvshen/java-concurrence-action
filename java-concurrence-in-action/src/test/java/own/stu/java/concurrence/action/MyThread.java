package own.stu.java.concurrence.action;

import java.util.concurrent.TimeUnit;

public class MyThread {

    static class OwnThread extends Thread {
        public void run(){
            super.run();
            for(int i=0; i<5000; i++){
                System.out.println("i="+(i+1));
            }
        }
    }

    static class OwnCanStopThread extends Thread {
        public void run(){
            super.run();
            for(int i=0; i<5000; i++){
                if(this.interrupted()){
                    System.out.println("线程已经终止， for循环不再执行");
                    break;
                }
                System.out.println("i="+(i+1));
            }
        }
    }

    static class OwnCanStopThread_Sec extends Thread {
        public void run(){
            super.run();
            for(int i=0; i<5000; i++){
                if(this.interrupted()){
                    System.out.println("线程已经终止， for循环不再执行");
                    break;
                }
                System.out.println("i="+(i+1));
            }

            System.out.println("这是for循环外面的语句，也会被执行");
        }
    }

    static class OwnCanStopThread_Thr extends Thread {
        public void run(){
            super.run();
            try {
                for(int i=0; i<5000; i++){
                    if(this.interrupted()){
                        System.out.println("线程已经终止， for循环不再执行");
                        throw new InterruptedException();
                    }
                    System.out.println("i="+(i+1));
                }
                System.out.println("这是for循环外面的语句，也会被执行");
            }catch (InterruptedException e){
                System.out.println("进入MyThread.java类中的catch了。。。");
                e.printStackTrace();
            }
        }
    }

    static class OwnCanStopThread_Four extends Thread {
        public void run(){
            super.run();

            try {
                System.out.println("线程开始。。。");
                Thread.sleep(200000);
                System.out.println("线程结束。");
            } catch (InterruptedException e) {
                System.out.println("在沉睡中被停止, 进入catch， 调用isInterrupted()方法的结果是：" + this.isInterrupted());
                e.printStackTrace();
            }

        }
    }

    static class OwnCanStopThread_Five extends Thread {
        public void run(){
            super.run();
            try {
                System.out.println("线程开始。。。");
                for(int i=0; i<10000; i++){
                    System.out.println("i=" + i);
                }
                TimeUnit.SECONDS.sleep(6);
                System.out.println("线程结束。");
            } catch (InterruptedException e) {
                System.out.println("先停止，再遇到sleep，进入catch异常");
                e.printStackTrace();
            }

        }
    }

    public static void main_2(String args[]){
        Thread thread = new OwnThread();
        thread.start();
        try {
            Thread.sleep(10);
            thread.interrupt();
            System.out.println("=====================" + Thread.interrupted());
            System.out.println("=====================" + Thread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main_3(String[] args) {
        Thread.currentThread().interrupt();
        System.out.println("stop 1??" + Thread.interrupted());
        System.out.println("stop 2??" + Thread.interrupted());

        System.out.println("End");
    }

    public static void main_4(String args[]){
        Thread thread = new OwnThread();
        thread.start();
        thread.interrupt();
        System.out.println("stop 1??" + thread.isInterrupted());
        System.out.println("stop 2??" + thread.isInterrupted());
    }

    public static void main(String args[]){
//        Thread thread = new OwnCanStopThread();
//        Thread thread = new OwnCanStopThread_Sec();
//        Thread thread = new OwnCanStopThread_Thr();
//        Thread thread = new OwnCanStopThread_Four();
        Thread thread = new OwnCanStopThread_Five();
        thread.start();
        try {
            Thread.sleep(10);
            thread.interrupt();
            System.out.println("=====================" + Thread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}