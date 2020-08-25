package own.stu.highConcurrence.cacheconsistence.dao;

import org.apache.ibatis.annotations.Param;
import own.stu.highConcurrence.cacheconsistence.model.IdGene;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author HW
 * @since 2020-08-23
 */
public interface IdGeneMapper extends BaseMapper<IdGene> {

    IdGene getByKey(@Param("key") String key);
}
