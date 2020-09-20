package own.stu.highConcurrence.cacheconsistence.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import own.stu.highConcurrence.cacheconsistence.model.IdGene;
import own.stu.highConcurrence.cacheconsistence.dao.IdGeneMapper;
import own.stu.highConcurrence.cacheconsistence.service.IIdGeneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author HW
 * @since 2020-08-23
 */
@Service
public class IdGeneServiceImpl extends ServiceImpl<IdGeneMapper, IdGene> implements IIdGeneService {

    @Autowired
    private IdGeneMapper idGeneMapper;

    public IdGene getByKey(String key) {
        return idGeneMapper.getByKey(key);
    }
}
