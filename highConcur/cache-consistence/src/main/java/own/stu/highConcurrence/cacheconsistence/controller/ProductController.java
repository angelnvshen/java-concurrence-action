package own.stu.highConcurrence.cacheconsistence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.highConcurrence.cacheconsistence.model.Product;
import own.stu.highConcurrence.cacheconsistence.serivec.ProductService;

@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/get")
    public Product get(Integer productId) {
        Product product = productService.get(productId);
        return product;
    }

    @RequestMapping("/update")
    public String updateStore(Integer productId, Integer num) {
        int result = productService.updateStore(productId, num);
        return "SUCCESS";
    }
}
