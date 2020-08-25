package own.stu.highConcurrence.cacheconsistence.service;

import own.stu.highConcurrence.cacheconsistence.model.IdGene;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author HW
 * @since 2020-08-23
 */
public interface IIdGeneService extends IService<IdGene> {

    IdGene getByKey(String key);
}
