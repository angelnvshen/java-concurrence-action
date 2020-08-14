package own.stu.highConcurrence.cacheconsistence.service;

import own.stu.highConcurrence.cacheconsistence.model.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author HW
 * @since 2020-08-14
 */
public interface IProductService extends IService<Product> {
    int updateStore(Integer productId, Integer num);
}
