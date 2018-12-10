package own.stu.tkmybatis.demo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import own.stu.tkmybatis.demo.common.dao.MyMapper;
import own.stu.tkmybatis.demo.model.Country;

/**
 * @author Eduardo Macarron
 */
@Mapper
public interface CountryDao extends MyMapper<Country> {

  List<Country> findAll();

}
