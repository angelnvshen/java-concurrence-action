package own.stu.highConcurrence.cacheconsistence.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import own.stu.highConcurrence.cacheconsistence.model.Product;
import own.stu.highConcurrence.cacheconsistence.dao.ProductMapper;
import own.stu.highConcurrence.cacheconsistence.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    private ProductMapper productMapper;

    @Override
    public int updateStore(Integer productId, Integer num) {
        return productMapper.updateStore(productId, num);
    }
}
