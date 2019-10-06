package own.stu.tkmybatis.demo.service;

import own.stu.tkmybatis.demo.common.service.BaseService;
import own.stu.tkmybatis.demo.model.CostRecord;

import java.util.List;

public interface CostRecordService extends BaseService<CostRecord> {
    List<CostRecord> groupBy(CostRecord costRecord);

    List<CostRecord> selectByCostRecord(CostRecord CostRecord, int page, int rows);
}
