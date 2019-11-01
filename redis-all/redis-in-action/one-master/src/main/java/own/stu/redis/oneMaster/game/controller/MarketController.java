package own.stu.redis.oneMaster.game.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.redis.oneMaster.game.model.ItemInfo;
import own.stu.redis.oneMaster.game.service.MarketService_v1;

import java.util.Objects;

@Slf4j
@RequestMapping("market")
@RestController
public class MarketController {

    @Autowired
    private MarketService_v1 marketServiceV1;

    @RequestMapping("addInventoryForUser")
    public String addInventory(Integer userId) {

        marketServiceV1.fakeAddItem(userId);

        return "success";
    }

    @RequestMapping("sell-item")
    public String sellItem(Integer userId, String item, long price) {
        Assert.notNull(userId, "userId is null");
        Assert.notNull(item, "item is null");
        Assert.notNull(price, "price is null");
        return marketServiceV1.sellItem(userId, item, price) + "";
    }

    @RequestMapping("purchase-item")
    public String purchaseItem(String itemName, Integer buyerId) {
        ItemInfo itemFromMarket = marketServiceV1.getItemFromMarket(itemName);
        if (itemFromMarket == null) {
            return "false -> no item in market";
        }

        if (Objects.equals(itemFromMarket.getUserId(), buyerId)) {
            return "false -> buyer is seller";
        }

        log.info("purchase-item : {} , buyerId : {}", itemFromMarket, buyerId);
        return marketServiceV1.purchaseItem(itemFromMarket.getItemName(), buyerId,
                itemFromMarket.getUserId(), itemFromMarket.getPrice()) + "";
    }
}
