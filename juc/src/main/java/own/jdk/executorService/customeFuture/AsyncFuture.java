package own.jdk.executorService.customeFuture;

public class AsyncFuture<T> implements Future {

    T result;

    volatile boolean isDone = false;

    public void done(T result) {
        synchronized (this) {
            this.result = result;
            isDone = true;
            notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (!isDone) {
                wait();
            }
        }

        return result;
    }
}
