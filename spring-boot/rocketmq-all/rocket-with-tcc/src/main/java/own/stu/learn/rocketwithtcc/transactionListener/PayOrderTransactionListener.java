package own.stu.learn.rocketwithtcc.transactionListener;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import own.stu.learn.rocketwithtcc.model.OrderModel;

@Component
@RocketMQMessageListener(
        consumerGroup = "${order.create.tx.group}", topic = "order", selectorExpression = "create"
)
public class PayOrderTransactionListener implements RocketMQListener<String> {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void onMessage(String message) {
        OrderModel orderModel = JSON.parseObject(message, OrderModel.class);
        if (orderModel == null) {
            return;
        }

        createPayOrder(orderModel);
    }

    /**
     * 需要成功，失败时尝试重新消费消息
     *
     * @param orderModel
     */
    private void createPayOrder(OrderModel orderModel) {
        redisTemplate.opsForHash().put("pay", orderModel.getOrderId(), orderModel.getMoney());
    }
}
