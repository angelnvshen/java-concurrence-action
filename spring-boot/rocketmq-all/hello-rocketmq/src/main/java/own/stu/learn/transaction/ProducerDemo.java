package own.stu.learn.transaction;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import own.stu.learn.queued.model.OrderStep;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static own.stu.learn.base.BaseProductDemo.sleep;
import static own.stu.learn.queued.model.OrderStep.buildOrders;

public class ProducerDemo {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        TransactionMQProducer producer = new TransactionMQProducer("group1");
        producer.setNamesrvAddr("192.168.0.116:9876;192.168.0.117:9876;");
        producer.start();

        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                String argStr = (String) arg;
                if (Objects.equals(argStr, "L1"))
                    return LocalTransactionState.COMMIT_MESSAGE;

                if (Objects.equals(argStr, "L2"))
                    return LocalTransactionState.ROLLBACK_MESSAGE;

                return LocalTransactionState.UNKNOW;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                System.out.println(new String(msg.getBody()));
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        List<String> list = Arrays.asList("L1", "L2", "L3");
        list.stream().forEach(s -> {
            Message message = new Message("hello-world", "tag-1", ("hello-transcation-" + s).getBytes());
            try {
                TransactionSendResult transactionSendResult = producer.sendMessageInTransaction(message, s);
                System.out.println(transactionSendResult);
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        });

        sleep(100);
        producer.shutdown();
    }
}
