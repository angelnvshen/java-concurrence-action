package own.stu.spring.cloud.order.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import own.stu.spring.cloud.order.remote.callback.ProductServiceRemoteCallBack;
import own.stu.springcloud.product.domain.Product;

import java.util.List;

@FeignClient(value = "product", fallback = ProductServiceRemoteCallBack.class)
public interface ProductServiceRemote {

    @RequestMapping("/product/get-all")
    List<Product> findAll();

    @RequestMapping("/product/get-by-id")
    Product getById(@RequestParam("productId") Integer id);

}
