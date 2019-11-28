package own.jdk.lamda;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.function.Consumer;

public class FunctionalDemo {

    public static void main2(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 5, 5, 1);
        Consumer<Integer> consumer = x -> {
            System.out.println("------");
        };
        consumer = consumer.andThen(x -> {
            System.out.println("******");
        });
        list.stream().forEach(consumer);
    }

    public static void main(String[] args) {
        Hello hello = () -> System.out.println("hello");
        hello.sayHello();

        HelloWorld helloWorld = () -> System.out.println("hello");
        helloWorld.sayNewWorld();

    }

    @FunctionalInterface
    interface HelloWorld extends Hello {
        void sayNewWorld();

        @Override
        default void sayHello() {
            System.out.println(" ===== hello ======");
        }
    }

    @FunctionalInterface
    interface Hello {
        void sayHello();
    }
}
