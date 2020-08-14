package own.stu.highConcurrence.cacheconsistence.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import own.stu.highConcurrence.cacheconsistence.dao.ProductDao;
import own.stu.highConcurrence.cacheconsistence.model.Product;

@Service
public class ProductService {

    @Autowired
    private RedisTemplate<String, Product> redisTemplate;

    @Autowired
    private ProductDao productDao;

    private static String product_id_redis_key = "product:id:%d";

    private static String getProductIdRedisKey(Integer productId) {
        return String.format(product_id_redis_key, productId);
    }

    public Product get(Integer id) {
        assert id != null;
        Product product = (Product) redisTemplate.opsForValue().get(getProductIdRedisKey(id));
        if (product == null) {
            product = productDao.getOne(id);
        }

        redisTemplate.opsForValue().set(getProductIdRedisKey(id), product);
        return product;
    }

    public int updateStore(Integer productId, Integer num) {
        return productDao.updateStore(productId, num);
    }

}
