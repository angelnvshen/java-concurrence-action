package own.jdk.multiThreadPattern.chapter5_twoPhaseTermination;

public class DelegatingTerminableThread extends AbstractTerminableThread {

    private final Runnable task;

    public DelegatingTerminableThread(Runnable task) {
        this.task = task;
    }

    @Override
    protected void doRun() throws Exception {
        this.task.run();
    }

    public static AbstractTerminableThread of(Runnable task) {
        DelegatingTerminableThread ret = new DelegatingTerminableThread(
                task);
        return ret;
    }
}
