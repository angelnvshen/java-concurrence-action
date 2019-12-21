package own.stu.learn.rocketwithtcc.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderModel implements Serializable {

    String orderId;

    String money;

    public OrderModel(String orderId, String money) {
        this.orderId = orderId;
        this.money = money;
    }

    public OrderModel() {
    }
}
