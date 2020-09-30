package own.rxjava2.operator.transfor;

import com.google.common.collect.Lists;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Function;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo {

    public static void main(String[] args) {
        /*Observable<Integer> observable = Observable.just(1);
        Observable<String> map = observable.map((a) -> 1 + "2");
        map.subscribe(a -> System.out.println(a));*/

        /*Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//                .buffer(3, 2)
                .buffer(2, 3)
//                .buffer(3)
                .subscribe(System.out::println);*/

        /*Observable.just(1, 2, 3, 4)
                .flatMap(new Function<Integer, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Integer integer) throws Exception {
                        return Observable.just("flatMap : " + integer);
                    }
                })
                .subscribe(System.out::println);*/


        /*List<String> list = Lists.newArrayList("hello", "world");
        List<String[]> collect = list.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

        System.out.println(collect);

        List<String> collect1 = Stream.of(new String[]{"Hello", "World"})
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .collect(Collectors.toList());

        System.out.println(collect1);*/

        /*任何一个要发送出来的数据都 是以上次发迭出来的数据和这次源 Observable 发送的数据作为输入，
        并应用同 一个函数计算 后的结果。*/
        Observable.fromArray(1,2,3,4,5,6)
                .scan( (a, b) -> a * b)
                .subscribe(System.out::println);
    }
}
