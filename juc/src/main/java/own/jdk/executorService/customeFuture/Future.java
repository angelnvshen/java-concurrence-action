package own.jdk.executorService.customeFuture;

public interface Future<T> {
    T get() throws InterruptedException;
}
