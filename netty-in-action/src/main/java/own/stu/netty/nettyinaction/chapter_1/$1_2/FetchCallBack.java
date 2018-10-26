package own.stu.netty.nettyinaction.chapter_1.$1_2;

/**
 * Created by CHANEL on 2018/7/14.
 */
public interface FetchCallBack {
    void onData(Data data) throws Exception;
    void onError(Throwable cause);
}
