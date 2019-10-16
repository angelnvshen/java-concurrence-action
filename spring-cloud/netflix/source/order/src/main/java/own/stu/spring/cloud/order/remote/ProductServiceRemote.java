package own.stu.spring.cloud.order.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import own.stu.springcloud.product.domain.Product;

import java.util.List;

@FeignClient("product")
@RequestMapping("product")
public interface ProductServiceRemote {

    @RequestMapping("get-all")
    List<Product> findAll();

    @RequestMapping("get-by-id")
    Product getById(@RequestParam("productId") Integer id);

}
