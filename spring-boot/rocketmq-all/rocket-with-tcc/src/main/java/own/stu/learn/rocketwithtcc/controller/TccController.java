package own.stu.learn.rocketwithtcc.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import own.stu.learn.rocketwithtcc.model.OrderModel;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Slf4j
@Controller
public class TccController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${order.create.tx.group}")
    private String txProducerGroup;

    @ResponseBody
    @RequestMapping("create-order")
    public String order(String orderId, String money) {

        OrderModel orderModel = new OrderModel(orderId, money);
        Message<OrderModel> order = MessageBuilder.withPayload(orderModel).build();

        TransactionSendResult tx = rocketMQTemplate.sendMessageInTransaction(txProducerGroup, "order:create", order, null);

        log.info("发送交易信息到mq:{}, 结果:{}", orderModel, tx);
        return "SUCCESS";
    }

    @ResponseBody
    @RequestMapping("show-order")
    public List<OrderModel> showOrder() {
        Set<String> orderId = redisTemplate.opsForZSet().range("orderId", 0, -1);
        Map<Object, Object> pay = redisTemplate.opsForHash().entries("pay");

        List<OrderModel> orderList = new ArrayList<>();
        if (orderId.size() == pay.size()) {
            pay.entrySet().stream().forEach(entry ->
                    orderList.add(new OrderModel((String) entry.getKey(), (String) entry.getValue())));

        } else {
            log.info("orderId num :{}, but payInfo num : {}", orderId.size(), pay.size());
        }
        return orderList;
    }

    @ResponseBody
    @RequestMapping("clear-order")
    public String clearOrder() {

        redisTemplate.delete(Arrays.asList("orderId", "pay"));
        return "SUCCESS";
    }

    /**
     * =============
     * mock
     * =============
     */
    @Autowired
    private ExecutorService executorService;

    @ResponseBody
    @RequestMapping("mock-create")
    public String mockCreateOrder() {

        AtomicInteger count = new AtomicInteger();

        IntStream.range(0, 3).forEach(num -> executorService.submit(() -> {
            for (int i = 0; i < 100; i++) {
                Integer orderId = count.getAndIncrement();
                order(orderId + "", orderId * 100 + "");
            }
        }));

        return "SUCCESS";
    }
}
