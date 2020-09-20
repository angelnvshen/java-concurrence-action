package own.stu.shardingjdbc.shardingjdbcshopdemo.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfo {

    private Long productInfoId;

    private Long storeId;

    private String productName;

    private String spec;

    private String regionCode;

    private BigDecimal price;

    private String imageUrl;

    private String description;

    private String regionName;
}
