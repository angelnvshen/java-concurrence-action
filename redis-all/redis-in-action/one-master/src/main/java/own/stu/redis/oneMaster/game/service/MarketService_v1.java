package own.stu.redis.oneMaster.game.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import own.stu.redis.oneMaster.autoComplete.controller.AutoCompleteController;
import own.stu.redis.oneMaster.game.model.ItemInfo;

import java.time.Instant;
import java.util.*;

@Slf4j
@Service
public class MarketService_v1 {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    static String marketKey = "market:";

    public ItemInfo getItemFromMarket(String itemName) {
        Assert.notNull(itemName, "itemName is null ");

        Double score = redisTemplate.boundZSetOps(marketKey).score(itemName);
        if (score == null) {
            log.info(itemName + " not in market ");
            return null;
        }
        int lastDotIndex = itemName.lastIndexOf(".");
        return new ItemInfo(itemName.substring(0, lastDotIndex),
                Integer.valueOf(itemName.substring(lastDotIndex + 1)), score.longValue());
    }

    public boolean purchaseItem(String itemName, Integer buyer, Integer seller, long lprice) {
        String buyerKey = "user:" + buyer;
        String buyerInventoryKey = "inventory:" + buyer;
        String sellerKey = "user:" + seller;

        String itemKey = String.format("%s.%d", itemName, seller);

        fillUser(seller);

        Object execute = redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {

                // 对 市场 和 买家进行监视
                operations.watch(Lists.newArrayList(marketKey, buyerKey));
                Double score = operations.boundZSetOps(marketKey).score(itemKey);
                if (score == null) {
                    operations.unwatch();
                    return false;
                }

                long price = score.longValue();
                Object funds = operations.boundHashOps(buyerKey).get("funds");
                if (price != lprice || price > Long.valueOf((String) funds)) {
                    operations.unwatch();
                    return false;
                }
                operations.multi();
                operations.boundHashOps(sellerKey).increment("funds", price);
                operations.boundHashOps(buyerKey).increment("funds", -price);
                operations.boundSetOps(buyerInventoryKey).add(itemName);
                operations.boundZSetOps(marketKey).remove(itemKey);
                operations.exec();
                return true;
            }
        });
        return (boolean) execute;
    }

    // 不关心数量 v1
    public boolean sellItem(Integer userId, String itemName, long price) {

        String inventory = String.format("inventory:%s", userId);
        String item = String.format("%s.%d", itemName, userId);

        long endTime = Instant.now().getEpochSecond() + 5;

        Object execute = redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {

                operations.watch(inventory);
                BoundSetOperations setOperations = operations.boundSetOps(inventory);
                if (!setOperations.isMember(itemName)) {
                    operations.unwatch();
                    return false;
                }
                Boolean addValue = operations.boundZSetOps("market:").add(item, price);
                if (addValue != null && addValue) {
                    setOperations.remove(inventory, itemName);
                }

                return true;
            }
        });

        return (boolean) execute;
    }

    private static List<String> itemInventory = Lists.newArrayList("台灯", "SKI-1", "AK_47", "FERRI_33", "ATOM", "算法2", "Java编程思想", "红楼梦", "三国", "MAC-book", "CHANNEL");
    private static Random random = new Random(new Date().getTime());

    public void fakeAddItem(Integer userId) {

        Assert.notNull(userId, "userId is null");

        fillUser(userId);

        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                for (int i = 0; i < 5; i++) {
                    String itemName = itemInventory.get(random.nextInt(itemInventory.size()));
                    operations.boundSetOps("inventory:" + userId).add(itemName);
                }
                operations.exec();
                return null;
            }
        });
    }

    private void fillUser(Integer userId) {
        Boolean hasUser = redisTemplate.hasKey("user:" + userId);
        if (hasUser != null && hasUser) {
            return;
        }
        String userName = AutoCompleteController.getRandomStr(5);
        redisTemplate.boundHashOps("user:" + userId).put("name", userName);
        redisTemplate.boundHashOps("user:" + userId).put("funds", "1000");
    }
}
