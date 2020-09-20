package own.stu.highConcurrence.cacheconsistence.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import own.stu.highConcurrence.cacheconsistence.model.Product;
import own.stu.highConcurrence.cacheconsistence.dao.ProductMapper;
import own.stu.highConcurrence.cacheconsistence.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author HW
 * @since 2020-08-14
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private RedisTemplate<String, Product> redisTemplate;

    @Autowired
    private ProductMapper productMapper;

    private static String product_id_redis_key = "product:id:%d";

    private static String getProductIdRedisKey(Integer productId) {
        return String.format(product_id_redis_key, productId);
    }

    public Product getById(Integer id) {
        assert id != null;
        Product product = null;
        try {
            product = redisTemplate.opsForValue().get(getProductIdRedisKey(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (product == null) {
            product = productMapper.selectById(id);
            try {
                redisTemplate.opsForValue().set(getProductIdRedisKey(id), product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    @Override
    public int updateStore(Integer productId, Integer num) {
        try {
            redisTemplate.delete(getProductIdRedisKey(productId));
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productMapper.updateStore(productId, num);
    }
}
