package own.stu.jdk9.flowapi;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class FlowDemo {
    public static void main(String[] args) throws InterruptedException {
        // 1:定义发布者
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        //2 ：定义订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<Integer>() {

            Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;

                subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                // 处理接收到的数据
                System.out.println(item);
                // 处理完成后，再请求新的数据
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {

                subscription.cancel();

                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        };

        //3 : 发布者和订阅者 建立订阅关系
        publisher.subscribe(subscriber);

        // 4 : 生产数据，并发布
        int data = 111;
        publisher.submit(data);

        // 5 : 关闭发布者, 正式环境中，应该放 finally 或者 使用try-resource 确保关闭
        publisher.close();

//        Thread.currentThread().join();

        TimeUnit.SECONDS.sleep(2);
    }
}
