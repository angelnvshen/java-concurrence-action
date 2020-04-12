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
public interface OrderDao {

    @Insert("insert into t_order (price, user_id, status) values(#{price}, #{userId}, #{status})")
    int insert(@Param("price") BigDecimal price, @Param("userId") Long userId, @Param("status") Integer status);

    @Select("<script> " +
            "select * from t_order t " +
            "where t.order_id in " +
            "<foreach collection='orderIds' open='(' separator=',' close=')' item='id' > " +
            "${id}" +
            "</foreach>" +
            "</script>")
    List<Map> selectByOrderIds(@Param("orderIds") List<Long> orderIds);

    @Select("<script> " +
            "select * from t_order t " +
            "where t.user_id = #{userId} " +
            "and t.order_id in " +
            "<foreach collection='orderIds' open='(' separator=',' close=')' item='id' > " +
            "${id}" +
            "</foreach>" +
            "</script>")
    List<Map> selectByOrderIdAndUserId(@Param("orderIds") List<Long> orderIds, @Param("userId") Long userId);
}
