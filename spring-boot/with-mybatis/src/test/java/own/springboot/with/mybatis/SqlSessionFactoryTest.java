package own.springboot.with.mybatis;

import java.io.IOException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import own.springboot.with.mybatis.mapper.CountryMapper;
import own.springboot.with.mybatis.model.Country;

public class SqlSessionFactoryTest {

  @Test
  public void test() throws IOException {

    ClassPathResource resource = new ClassPathResource("mybatis/mybtais-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource.getInputStream());
    SqlSession sqlSession = sqlSessionFactory.openSession();
//    Country country = sqlSession.selectOne("own.springboot.with.mybatis.mapper.CountryMapper.selectOne", 1);
    CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
    Country country = countryMapper.selectOne(1);
    System.out.println(country);
  }
}
