package own.stu.tkmybatis.demo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import own.stu.tkmybatis.demo.common.dao.MyMapper;
import own.stu.tkmybatis.demo.model.Area;
import own.stu.tkmybatis.demo.model.AreaExample;

public interface AreaMapper extends MyMapper<Area> {
    long countByExample(AreaExample example);

    int deleteByExample(AreaExample example);

    List<Area> selectByExample(AreaExample example);

    int updateByExampleSelective(@Param("record") Area record, @Param("example") AreaExample example);

    int updateByExample(@Param("record") Area record, @Param("example") AreaExample example);
}