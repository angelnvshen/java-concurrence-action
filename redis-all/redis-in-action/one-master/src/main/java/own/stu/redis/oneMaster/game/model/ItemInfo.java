package own.stu.redis.oneMaster.game.model;

import lombok.Data;

@Data
public class ItemInfo {

    private String itemName;

    private Integer userId;

    private Long price;

    public ItemInfo() {
    }

    public ItemInfo(String itemName, Integer userId, Long price) {
        this.itemName = itemName;
        this.userId = userId;
        this.price = price;
    }
}
