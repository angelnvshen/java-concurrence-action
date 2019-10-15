package own.stu.spring.cloud.order.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private Integer productId;

    private String productName;

    private String tradeNo;

    private int price;

    private Date createTime;

    private Integer userId;

    private String userName;
}