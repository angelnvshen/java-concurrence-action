package own.stu.shardingjdbc.shardingjdbc.simple.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface UserDao {

    @Insert("insert into t_user (full_name, user_type) values(#{fullName}, #{userType})")
    int insert(@Param("fullName") String fullName, @Param("userType") String userType);

    @Select("<script> " +
            "select * from t_user t " +
            "where t.user_id in " +
            "<foreach collection='userIds' open='(' separator=',' close=')' item='id' > " +
            "${id}" +
            "</foreach>" +
            "</script>")
    List<Map> selectByUserIds(@Param("userIds") List<Long> userIds);

    @Select("<script> " +
            "select t.*, d.value from t_user t " +
            "left join t_dict d on t.user_type = d.code " +
            "where t.user_id in " +
            "<foreach collection='userIds' open='(' separator=',' close=')' item='id' > " +
            "${id}" +
            "</foreach> " +
            "and d.type = 'user_type' " +
            "</script>")
    List<Map> selectByUserIds_2(@Param("userIds") List<Long> userIds);
}
