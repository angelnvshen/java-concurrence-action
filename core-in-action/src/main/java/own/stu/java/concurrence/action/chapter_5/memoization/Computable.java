package own.stu.java.concurrence.action.chapter_5.memoization;

public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
