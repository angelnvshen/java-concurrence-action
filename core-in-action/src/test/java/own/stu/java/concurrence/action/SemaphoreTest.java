package own.stu.java.concurrence.action;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by CHANEL on 2018/5/19.
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        BoundedHashSet<String> boundedHashSet = new BoundedHashSet<>(10);
        Thread thread = new Thread(()->{
           for(int i = 0;i<20;i++) {
               try {
                   boundedHashSet.add("i" + i);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        Thread thread2 = new Thread(()->{
            for(int i = 0;i<20;i++) {
                boundedHashSet.remove("i" + i);
            }
        });

        thread.start();
        thread2.start();
    }

}

class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.sem = new Semaphore(bound);
        this.set = Collections.synchronizedSet(new HashSet<T>());
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded)
                sem.release();
        }
    }

    public boolean remove(T o){
        boolean wasRemove = set.remove(o);
        if(wasRemove)
            sem.release();
        return wasRemove;
    }
}
