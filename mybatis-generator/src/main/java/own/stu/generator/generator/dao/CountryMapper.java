package own.stu.generator.generator.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import own.stu.generator.generator.model.Country;
import own.stu.generator.generator.model.CountryExample;

public interface CountryMapper {
    long countByExample(CountryExample example);

    int deleteByExample(CountryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Country record);

    int insertSelective(Country record);

    List<Country> selectByExample(CountryExample example);

    Country selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Country record, @Param("example") CountryExample example);

    int updateByExample(@Param("record") Country record, @Param("example") CountryExample example);

    int updateByPrimaryKeySelective(Country record);

    int updateByPrimaryKey(Country record);
}