package own.stu.java.concurrence.action.chapter_5.memoization;

import java.util.Map;
import java.util.concurrent.*;

//存在一个缺陷，仍然存在两个线程计算出相同值的漏洞 if代码块仍然是非原子的“先检查后执行”操作
public class Memoizer3<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }


    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> result = cache.get(arg);
        if(result == null){
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<>(eval);
            result = ft;
            cache.put(arg, result);
            ft.run();
        }
        try {
            return result.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
