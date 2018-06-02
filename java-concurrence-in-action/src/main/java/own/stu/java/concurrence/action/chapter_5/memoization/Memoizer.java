package own.stu.java.concurrence.action.chapter_5.memoization;

import java.util.Map;
import java.util.concurrent.*;

//当缓存是future时，而不是值，会到导致缓存污染的问题
public class Memoizer<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
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

            result = cache.putIfAbsent(arg, ft);
            if(result == null){
                result = ft;
                ft.run();
            }

            result = ft;
            cache.put(arg, result);
            ft.run();
        }
        try {
            return result.get();
        }catch (CancellationException e) {
            cache.remove(arg, result);
        }catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
