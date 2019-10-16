package own.stu.spring.cloud.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import own.stu.spring.cloud.order.model.Order;
import own.stu.spring.cloud.order.model.OrderMessage;
import own.stu.spring.cloud.order.remote.ProductServiceRemote;
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

    @Autowired()
    private ProductServiceRemote productServiceRemote;

    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "saveFailCallBack")
    public OrderMessage save(Integer productId) {
        Order order = new Order();

        // Product product = restTemplate.getForObject("http://product/product/get-by-id?productId=" + productId, Product.class);
        Product product = productServiceRemote.getById(productId);
        System.out.println(product);

        //int i = 100 / 0; // exception By error

        order.setProductId(product.getId());
        order.setProductName(product.getName());
        order.setPrice(product.getPrice());
        order.setCreateTime(new Date());

        return OrderMessage.success(orderService.save(order));
    }

    public OrderMessage saveFailCallBack(Integer productId, Throwable e) {
        System.out.println("order-save service is not running, caused by " + e.getMessage());

        // TODO sendNotice to Developers async

        return OrderMessage.fail();
    }
}
