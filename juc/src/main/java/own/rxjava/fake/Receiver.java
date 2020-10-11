package own.rxjava.fake;

/**
 * 接收信息的人
 * @param <T>
 */
public abstract class Receiver<T> implements Calling, Callee<T> {

    private volatile boolean unCalled;

    @Override
    public void unCall() {
        unCalled = true;
    }

    @Override
    public boolean isUnCalled() {
        return unCalled;
    }
}
