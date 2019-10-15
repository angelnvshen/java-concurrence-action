package own.stu.springcloud.product.service.impl;

import org.springframework.stereotype.Service;
import own.stu.springcloud.product.domain.Product;
import own.stu.springcloud.product.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    static Map<Integer, Product> repository = new HashMap<>();

    static {

        Product product1 = new Product(1, "iphone-xs", 9999);
        Product product2 = new Product(2, "法拉利", 1119999);
        Product product3 = new Product(3, "lamer", 3999);
        Product product4 = new Product(4, "算法", 89);
        Product product5 = new Product(5, "别墅", 229999);
        Product product6 = new Product(6, "冲锋衣", 299);


        repository.put(product1.getId(), product1);
        repository.put(product2.getId(), product2);
        repository.put(product3.getId(), product3);
        repository.put(product4.getId(), product4);
        repository.put(product5.getId(), product5);
        repository.put(product6.getId(), product6);
    }

    @Override
    public List<Product> findAll() {
        return repository.values().stream().collect(Collectors.toList());
    }

    @Override
    public Product getById(Integer id) {
        return repository.get(id);
    }
}
