package own.jdk.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Treiber Stack Algorithm是一个可扩展的无锁栈，利用细粒度的并发原语CAS来实现的
 * Non-blocking stack 的一个实现 —— Treiber Stack
 */
public class ConcurrentStack<E> {

    private AtomicReference<Node<E>> top = new AtomicReference<>();

    public Node<E> getHead() {
        return top.get();
    }

    public void push(E elem) {
        Node<E> newHead = new Node<>(elem);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = top.get();
            if (oldHead == null) {
                return null;
            }
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead));

        return oldHead.item;
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ConcurrentStack<String> stack = new ConcurrentStack<>();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {
            for (int i = 0; i < 10000; i++) {
                stack.push(i + "");
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.submit(() -> {
            for (int i = 20000; i < 30000; i++) {

                stack.push(i + "");

                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.submit(() -> {
            for (int i = 90000; i < 100000; i++) {
                stack.push(i + "");

                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        TimeUnit.SECONDS.sleep(3);
        Node<String> head = stack.getHead();
        if (head != null) {
            System.out.println(head.item);
        }
        int count = 1;
        while (head.next != null) {
            System.out.println(head.item);
            head = head.next;
            count ++;
        }
        System.out.println(" ======= ");
        System.out.println(count);
        executorService.shutdown();
    }
}
