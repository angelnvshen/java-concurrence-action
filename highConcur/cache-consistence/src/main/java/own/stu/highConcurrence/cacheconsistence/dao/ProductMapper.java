package own.stu.highConcurrence.cacheconsistence.dao;

import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import own.stu.highConcurrence.cacheconsistence.model.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author HW
 * @since 2020-08-14
 */
public interface ProductMapper extends BaseMapper<Product> {

    int updateStore(@NonNull @Param("productId") Integer productId,
                    @NonNull @Param("num") Integer num);
}
