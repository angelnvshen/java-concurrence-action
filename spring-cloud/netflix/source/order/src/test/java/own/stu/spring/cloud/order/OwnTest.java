package own.stu.spring.cloud.order;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import own.stu.springcloud.product.domain.Product;

public class OwnTest {

    @Test
    public void test() {

        RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject("http://localhost:8071/product/get-by-id?productId=1", Product.class);
        System.out.println(product);
    }
}
