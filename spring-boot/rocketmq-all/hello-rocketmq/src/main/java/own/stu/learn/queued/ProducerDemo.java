package own.stu.learn.queued;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import own.stu.learn.queued.model.OrderStep;

import java.util.List;

import static own.stu.learn.base.BaseProductDemo.sleep;
import static own.stu.learn.queued.model.OrderStep.buildOrders;

public class ProducerDemo {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.0.116:9876;192.168.0.117:9876;");
        producer.start();

        List<OrderStep> orderSteps = buildOrders();

        for (OrderStep orderStep : orderSteps) {

            Message message = new Message("hello-world", "tag-1", orderStep.toString().getBytes());

            SendResult sendResult = producer.send(message, (mqs, msg, arg) -> {
                long orderId = (long) arg;
                return mqs.get((int) (orderId % mqs.size()));
            }, orderStep.getOrderId());

            System.out.println(sendResult);
        }

        sleep(10);
        producer.shutdown();
    }
}
