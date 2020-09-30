package own.rxjava2.operator.create;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CreateDemo {
    public static void main(String[] args) {


        // range().subscribe(System.out::println);

        /*Observable<Long> just = getJust();
        just.subscribe(System.out::println);
        just.subscribe(System.out::println);
        just.subscribe(System.out::println);

        Observable<Long> defer = getDefer();
        defer.subscribe(System.out::println);
        defer.subscribe(System.out::println);
        defer.subscribe(System.out::println);*/

        /*getFrom().subscribe(a -> System.out.println(a + System.currentTimeMillis()));
        System.out.println();
        Observable.just(1,2,3,4).subscribe(a -> System.out.println(a + System.currentTimeMillis()));*/

        // TODO
        getInterval().subscribe(System.out::println);
    }

    /**
     * 创建的 Observable 将依次发送从 n开始的 m个数
     */
    private static Observable range() {
        return Observable.range(0, 10);
    }

    private static Observable<Long> getJust() {
        return Observable.just(System.currentTimeMillis());
    }

    /*
     * 只有当有 Subscriber 来订阅的时候才会创建一个新 的 Observable 对象，
     * 也就是说每次订阅都会得到一个 刚创 建的最新的 Observable 对 象 ， 这可以 确保 Observable对象里的数据是最新的
     * */
    private static Observable<Long> getDefer() {
        return Observable.defer(new Callable<ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> call() throws Exception {
                return getJust();
            }
        });
    }

    /*
     * 参数对象是一个含有 10 个数字的数组，使用fromm 创建的 Observable就会发送 10次，
     * 每次发送一个数字，而使用 just创建的 Observable会一次就 将整个数组发送出去。
     * */
    private static Observable<Integer> getFrom() {
        return Observable.fromArray(1, 2, 3, 4);
    }

    // TODO
    private static Observable getInterval() {
        return Observable.interval(1, TimeUnit.SECONDS);
    }

    // TODO
    private static Observable getRepeat() {
        return Observable.timer(1, TimeUnit.SECONDS);
    }
}
