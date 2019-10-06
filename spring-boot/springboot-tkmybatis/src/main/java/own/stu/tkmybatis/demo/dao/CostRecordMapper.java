package own.stu.tkmybatis.demo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import own.stu.tkmybatis.demo.common.dao.MyMapper;
import own.stu.tkmybatis.demo.model.CostRecord;
import own.stu.tkmybatis.demo.model.CostRecordExample;

public interface CostRecordMapper extends MyMapper<CostRecord> {
    long countByExample(CostRecordExample example);

    int deleteByExample(CostRecordExample example);

    List<CostRecord> selectByExample(CostRecordExample example);

    int updateByExampleSelective(@Param("record") CostRecord record, @Param("example") CostRecordExample example);

    int updateByExample(@Param("record") CostRecord record, @Param("example") CostRecordExample example);

    List<CostRecord> groupBy(@Param("record") CostRecord record);
}