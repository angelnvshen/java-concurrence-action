package own.stu.spring.cloud.order.remote.callback;

import com.google.common.collect.Lists;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import own.stu.spring.cloud.order.remote.ProductServiceRemote;
import own.stu.springcloud.product.domain.Product;

import java.util.List;

@Component
public class ProductServiceRemoteCallBack implements ProductServiceRemote {

    public List<Product> findAll(){

        System.out.println("ProductServiceRemote findAll 远程调用异常");
        return Lists.newArrayList();
    }

    public Product getById(Integer id){
        System.out.println("ProductServiceRemote getById 远程调用异常");
        return null;
    }
}
