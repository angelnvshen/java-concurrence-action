package own.stu.shardingjdbc.shardingjdbc.simple.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface DictDao {

    @Insert("insert into t_dict (type, code, value) values(#{type}, #{code}, #{value})")
    int insert(@Param("type") String type, @Param("code") String code, @Param("value") String value);

    @Select(
            "select * from t_dict t " +
                    "where t.type = #{type} "
    )
    List<Map> selectByType(@Param("type") String type);

    @Delete("<script> " +
            "delete from t_dict " +
            "where dict_id in " +
            "<foreach collection='dictIds' open='(' separator=',' close=')' item='id' > " +
            "${id}" +
            "</foreach>" +
            "</script>")
    int deleteByIdList(@Param("dictIds") List<Long> dictIds);
}
