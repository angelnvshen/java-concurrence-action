package own.stu.springcloud.product.domain;

import lombok.Data;

@Data
public class Product {

    private Integer id;

    private String name;

    private Integer price;

    public Product() {
    }

    public Product(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
