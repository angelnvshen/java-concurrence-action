package own.jdk.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LazySingleton {
    private int id;
    private static LazySingleton instance;

    private LazySingleton() {
        this.id = new Random().nextInt(200) + 1;                 // (1)
    }

    public static LazySingleton getInstance() {
        if (instance == null) {                               // (2)
            synchronized (LazySingleton.class) {               // (3)
                if (instance == null) {                       // (4)
                    instance = new LazySingleton();           // (5)
                }
            }
        }
        return instance;                                      // (6)
    }

    public static LazySingleton getInstance1() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    LazySingleton temp = null;
                    try {
                        temp = new LazySingleton();
                    } catch (Exception e) {
                    }
                    //为什么要做这个看似无用的操作，因为这一步是为了让虚拟机执行到这一步的时会才对singleton赋值，
                    // 虚拟机执行到这里的时候，必然已经完成类实例的初始化。所以这种写法的DCL是安全的。
                    // 由于try的存在，虚拟机无法优化temp是否为null

                    if (temp != null) {
                        instance = temp;
                    }
                }
            }
        }

        return instance;
    }

    public int getId() {
        return id;                                            // (7)
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++)
            executorService.execute(() -> {
                System.out.println(" ======= " + LazySingleton.getInstance().getId());
            });
    }
}