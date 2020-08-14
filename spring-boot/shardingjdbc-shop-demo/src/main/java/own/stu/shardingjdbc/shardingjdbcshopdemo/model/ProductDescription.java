package own.stu.shardingjdbc.shardingjdbcshopdemo.model;

import lombok.Data;

@Data
public class ProductDescription {

    private Long id;

    private Long productInfoId;

    private Long storeId;

    private String description;
}
