package own.stu.springcloud.ribbonon.hand.iRule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RuleStrategy {

    List<String> info = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        List<String> info = new ArrayList();
        info.add("1");
        info.add("2");
        info.add("3");

        ListWrapper<String> wapper = new ListWrapper<>(info);

        for (int i = 0; i < 2; i++) {
            Thread tmp = new Thread(() -> {
                try {
                    for (; ; ) {
//                        System.out.println(Thread.currentThread().getName() + getRandomInfo(wapper));
                        System.out.println(Thread.currentThread().getName() + getRoundInfo(wapper));

                        TimeUnit.MILLISECONDS.sleep(200);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "t " + i + " - ");
            tmp.start();
        }


        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
                info.clear();
                info.add("4");
                info.add("5");
                info.add("6");
                wapper.setInfo(info);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        TimeUnit.SECONDS.sleep(5);
    }

    private static AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);

    private static <T> T getRoundInfo(ListWrapper<T> infoWrapper) {

        if (infoWrapper == null) {
            return null;
        }
        T result = null;
        int count = 0;// 统计次数，暂时不用

        while (result == null && count++ < 10) {
            List<T> info = infoWrapper.getInfo();
            int infoCount = info.size();
            if (infoCount == 0) {
                return null;
            }

            int nextServerIndex = incrementAndGetModulo(infoCount);
            result = info.get(nextServerIndex);
            if (result == null) {
                Thread.yield();
                continue;
            }
        }

        return result;
    }

    private static int incrementAndGetModulo(int count) {
        for (; ; ) {
            int cur = nextServerCyclicCounter.get();
            int next = (cur + 1) % count;
            if (nextServerCyclicCounter.compareAndSet(cur, next)) {
                return next;
            }
        }
    }


    private static <T> T getRandomInfo(ListWrapper<T> infoWrapper) {
        T result = null;

        while (result == null) {
            if (Thread.interrupted()) {
                return null;
            }

            List<T> info = infoWrapper.getInfo();
            int count = info.size();
            if (count == 0) {
                return null;
            }
            int index = chooseRandomInt(count);
            result = info.get(index);
            if (result == null) {
                Thread.yield();
                continue;
            }

            return result;
        }

        return result;
    }

    private static int chooseRandomInt(int count) {
        return ThreadLocalRandom.current().nextInt(count);
    }

    static class ListWrapper<T> {
        private volatile List<T> info;
        private Lock lock = new ReentrantLock();

        public ListWrapper(List<T> info) {
            setInfo(info);
        }

        public List<T> getInfo() {
            return info;
        }

        public void setInfo(List<T> info) {
            lock.lock();
            try {
                this.info = new CopyOnWriteArrayList<>(info);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main2(String[] args) throws InterruptedException {
        List<Integer> list = new CopyOnWriteArrayList();
        list.add(10);
        Thread t1 = new Thread(() -> {
            try {
                for (; ; ) {
                    System.out.println(list.get(0));

                    TimeUnit.MILLISECONDS.sleep(200);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        t1.start();


        Thread t2 = new Thread(() -> {
            try {

                TimeUnit.MILLISECONDS.sleep(1000);
                list.set(0, 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        t2.start();

        TimeUnit.SECONDS.sleep(5);
    }
}
