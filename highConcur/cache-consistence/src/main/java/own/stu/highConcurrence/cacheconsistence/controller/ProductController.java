package own.stu.highConcurrence.cacheconsistence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.highConcurrence.cacheconsistence.model.Product;
import own.stu.highConcurrence.cacheconsistence.service.IProductService;

@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping("/get")
    public Product get(Integer productId) {
        Product product = productService.getById(productId);
        return product;
    }

    @RequestMapping("/update")
    public String updateStore(Integer productId, Integer num) {
        int result = productService.updateStore(productId, num);
        return "SUCCESS";
    }
}
