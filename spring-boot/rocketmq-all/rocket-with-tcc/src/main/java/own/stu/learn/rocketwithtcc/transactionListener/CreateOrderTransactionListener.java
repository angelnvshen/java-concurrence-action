package own.stu.learn.rocketwithtcc.transactionListener;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import own.stu.learn.rocketwithtcc.model.OrderModel;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

@Component
@RocketMQTransactionListener(txProducerGroup = "${order.create.tx.group}")
public class CreateOrderTransactionListener implements RocketMQLocalTransactionListener {

    private static AtomicLong counter = new AtomicLong(1);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {

        try {
            String jsonString = new String((byte[]) msg.getPayload());
            OrderModel orderModel = JSON.parseObject(jsonString, OrderModel.class);
            if (orderModel == null) {
                throw new RuntimeException("orderModel is null");
            }
            boolean createOrder = mockCreateOrder(orderModel.getOrderId());
            if (!createOrder) {
                throw new RuntimeException("create order fail (random) ");
            }

            return RocketMQLocalTransactionState.COMMIT;

        } catch (Exception e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        try {
            String jsonString = new String((byte[]) msg.getPayload());
            OrderModel orderModel = JSON.parseObject(jsonString, OrderModel.class);

            Long orderId = redisTemplate.opsForZSet().rank("orderId", orderModel.getOrderId());
            if (orderId == null) {
                throw new RuntimeException("order hasn't been created ");
            }

            return RocketMQLocalTransactionState.COMMIT;

        } catch (Exception e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }


    private boolean mockCreateOrder(String orderId) {
        try {
            if (ThreadLocalRandom.current().nextBoolean()) {
                redisTemplate.opsForZSet().add("orderId", orderId, counter.getAndIncrement());
            } else {
                throw new RuntimeException(orderId + " 下单随机失败");
            }
            return true;
        } catch (Exception e) {
            // 模拟下单过程，异常需要数据库进行事务回滚，并抛出异常（此处不做处理）。
            e.printStackTrace();
            return false;
        }
    }
}
