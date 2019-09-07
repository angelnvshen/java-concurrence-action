package own.springboot.with.mybatis.mapper;

import org.springframework.stereotype.Repository;
import own.springboot.with.mybatis.model.Country;

//@Repository
public interface CountryMapper {

  Country selectOne(int id);
}
