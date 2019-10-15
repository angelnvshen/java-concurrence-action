package own.stu.spring.cloud.order.service;

import org.springframework.stereotype.Service;
import own.stu.spring.cloud.order.model.Order;

import java.util.UUID;

@Service
public class OrderService {

    public Order save(Order order) {
        order.setTradeNo(UUID.randomUUID().toString());
        return order;
    }
}
