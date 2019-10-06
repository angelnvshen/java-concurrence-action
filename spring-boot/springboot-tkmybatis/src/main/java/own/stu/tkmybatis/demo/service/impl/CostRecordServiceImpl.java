package own.stu.tkmybatis.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import own.stu.tkmybatis.demo.common.service.impl.BaseServiceImpl;
import own.stu.tkmybatis.demo.dao.CostRecordMapper;
import own.stu.tkmybatis.demo.model.CostRecord;
import own.stu.tkmybatis.demo.service.CostRecordService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CostRecordServiceImpl extends BaseServiceImpl<CostRecord> implements CostRecordService {

    @Autowired
    private CostRecordMapper costRecordMapper;

    public List<CostRecord> groupBy(CostRecord costRecord) {
        List<CostRecord> CostRecord = costRecordMapper.groupBy(costRecord);
        return CostRecord;
    }

    /**
     * 查询CostRecord信息
     * <p>
     * tk-mybatis 内置的 example 查询
     *
     * @param CostRecord 查询实体
     * @param page       页码
     * @param rows       行数
     * @return
     */
    @Override
    public List<CostRecord> selectByCostRecord(CostRecord CostRecord, int page, int rows) {

        Example example = new Example(CostRecord.class);
        Example.Criteria criteria = example.createCriteria();
        if (CostRecord.getId() != null) {
            criteria.andEqualTo("id", CostRecord.getId());
        }
        //分页查询
        PageHelper.startPage(page, rows);
        List<CostRecord> list = selectByExample(example);

        PageInfo<CostRecord> pageInfo = new PageInfo<>(list);

        return list;
    }
}
