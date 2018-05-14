package own.stu.java.concurrence.action.chapter_3;

public class NoVisibility {
    private static boolean ready;
    private static int num;

    private static class ReaderThread extends Thread{
        public void run(){
            while (! ready){
                Thread.yield();
            }
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
//        System.out.println(ready + " - " + num); //false - 0
        new ReaderThread().run();
        num = 42;
        ready = true;
    }
}
