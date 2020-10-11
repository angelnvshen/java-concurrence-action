package own.jdk.multiThreadInAction.chapter5;

public interface TaskRunnerSpec {
    public void init();

    public void submit(Runnable task) throws InterruptedException;
}