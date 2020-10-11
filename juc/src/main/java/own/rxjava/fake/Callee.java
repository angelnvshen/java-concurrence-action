package own.rxjava.fake;

/**
 * 接电话的人
 * @param <T>
 */
public interface Callee<T> {

    void onCompleted();

    void onReceiver(T t);

    void onError(Throwable t);
}
