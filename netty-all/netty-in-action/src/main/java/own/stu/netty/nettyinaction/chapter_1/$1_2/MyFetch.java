package own.stu.netty.nettyinaction.chapter_1.$1_2;

/**
 * Created by CHANEL on 2018/7/14.
 */
public class MyFetch implements Fetcher {

    final Data data;

    public MyFetch(Data data) {
        this.data = data;
    }

    @Override
    public void fetchData(FetchCallBack callBack) {
        try {
            callBack.onData(data);
        } catch (Exception e) {
            callBack.onError(e);
        }
    }
}
