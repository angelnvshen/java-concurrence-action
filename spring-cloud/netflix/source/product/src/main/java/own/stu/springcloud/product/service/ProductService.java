package own.stu.springcloud.product.service;

import own.stu.springcloud.product.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product getById(Integer id);
}
