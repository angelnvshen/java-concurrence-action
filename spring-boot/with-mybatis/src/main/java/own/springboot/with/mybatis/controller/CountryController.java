package own.springboot.with.mybatis.controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.springboot.with.mybatis.mapper.CountryMapper;
import own.springboot.with.mybatis.model.Country;

@RestController
@RequestMapping
public class CountryController {

//  @Autowired
//  private CountryMapper countryMapper;

  @Autowired
  private SqlSessionFactory sqlSessionFactory;

  @RequestMapping("get-ont")
  public Country getCountry(Integer id) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
    Country country = mapper.selectOne(id);
    return country;
  }
}
