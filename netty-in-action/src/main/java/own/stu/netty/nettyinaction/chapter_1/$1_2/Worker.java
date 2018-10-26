package own.stu.netty.nettyinaction.chapter_1.$1_2;

/**
 * Created by CHANEL on 2018/7/14.
 */
public class Worker {
    public void doWork(){
        Fetcher fetcher = new MyFetch(new Data(1, 0));
        fetcher.fetchData(new FetchCallBack() {
            @Override
            public void onData(Data data) throws Exception {
                System.out.println("Data received : " + data);
            }

            @Override
            public void onError(Throwable cause) {
                System.out.println("An error accour : " + cause);
            }
        });
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.doWork();
    }
}
