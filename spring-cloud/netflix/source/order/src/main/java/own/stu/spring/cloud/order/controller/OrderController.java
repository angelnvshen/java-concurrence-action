package own.stu.spring.cloud.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import own.stu.spring.cloud.order.model.Order;
import own.stu.spring.cloud.order.service.OrderService;
import own.stu.springcloud.product.domain.Product;

import java.util.Date;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("save")
    public Order save(Integer productId) {
        Order order = new Order();

        Product product = restTemplate.getForObject("http://product/product/get-by-id?productId=" + productId, Product.class);
        System.out.println(product);

        order.setProductId(product.getId());
        order.setProductName(product.getName());
        order.setPrice(product.getPrice());
        order.setCreateTime(new Date());

        return orderService.save(order);
    }
}
