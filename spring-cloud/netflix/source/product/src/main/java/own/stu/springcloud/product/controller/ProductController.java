package own.stu.springcloud.product.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import own.stu.springcloud.product.domain.Product;
import own.stu.springcloud.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Value("${server.port:8080}")
    private Integer port;

    @RequestMapping("get-all")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @RequestMapping("get-by-id")
    public Product getById(@RequestParam("productId") Integer id) {

        Product product = productService.getById(id);
        Product result = new Product();
        BeanUtils.copyProperties(product, result);
        result.setName(product.getName() + " ( " + port + " ) ");
        return result;
    }
}
