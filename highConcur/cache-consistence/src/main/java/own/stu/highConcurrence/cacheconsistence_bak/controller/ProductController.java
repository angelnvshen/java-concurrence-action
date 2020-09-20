package own.stu.highConcurrence.cacheconsistence_bak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.highConcurrence.cacheconsistence_bak.model.Product;
import own.stu.highConcurrence.cacheconsistence_bak.serivce.ProductService;

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
