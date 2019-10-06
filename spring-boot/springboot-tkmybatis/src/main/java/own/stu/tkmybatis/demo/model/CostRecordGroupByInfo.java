package own.stu.tkmybatis.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class CostRecordGroupByInfo {
    private String name;

    private Integer lastMinutes;
}