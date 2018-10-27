package own.stu.tkmybatis.demo.service.impl;

import com.github.pagehelper.PageHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import own.stu.tkmybatis.demo.common.service.impl.BaseServiceImpl;
import own.stu.tkmybatis.demo.dao.CountryDao;
import own.stu.tkmybatis.demo.model.Country;
import own.stu.tkmybatis.demo.model.CountryExample;
import own.stu.tkmybatis.demo.model.OwnExample;
import own.stu.tkmybatis.demo.model.OwnExample.OwnExampleCriteria;
import own.stu.tkmybatis.demo.service.CountryService;
import tk.mybatis.mapper.entity.Example;

@Service
public class CountryServiceImpl extends BaseServiceImpl<Country> implements CountryService {

  @Autowired
  private CountryDao countryDao;

  public List<Country> findAll() {
    List<Country> country = countryDao.findAll();
    return country;
  }

  /**
   * 查询country信息
   *
   * tk-mybatis 内置的 example 查询
   *
   * @param country 查询实体
   * @param page    页码
   * @param rows    行数
   * @return
   */
  @Override
  public List<Country> selectByCountry(Country country, int page, int rows) {

    Example example = new Example(Country.class);
    Example.Criteria criteria = example.createCriteria();
    if (!StringUtils.isEmpty(country.getCountryName())) {
      criteria.andLike("countryName", "%" + country.getCountryName() + "%");
    }
    if (!StringUtils.isEmpty(country.getCountryCode())) {
      criteria.andLike("countryCode", "%" + country.getCountryCode() + "%");
    }
    if (country.getId() != null) {
      criteria.andEqualTo("id", country.getId());
    }
    //分页查询
    PageHelper.startPage(page, rows);
    return selectByExample(example);
  }

  /**
   * 查询country信息
   *
   * generator 自动生成 example 查询
   *
   * @param country 查询实体
   * @param page    页码
   * @param rows    行数
   * @return
   */
  @Override
  public List<Country> selectByCountryTwo(Country country, int page, int rows) {
    CountryExample example = new CountryExample();
    CountryExample.Criteria criteria = example.createCriteria();
    if (!StringUtils.isEmpty(country.getCountryName())) {
      criteria.andCountryNameLike("%" +  country.getCountryName() + "%");
    }
    if (!StringUtils.isEmpty(country.getCountryCode())) {
      criteria.andCountryCodeLike("%" + country.getCountryCode() + "%");
    }
    if (country.getId() != null) {
      criteria.andIdEqualTo(country.getId());
    }
    //分页查询
    PageHelper.startPage(page, rows);
    return selectByExample(example);
  }

  @Override
  public List<Country> selectByCountryThree(Country country, int page, int rows) {
    OwnExample example = new OwnExample(Country.class);
    OwnExample.OwnExampleCriteria criteria = (OwnExampleCriteria) example.createCriteria();
    if (!StringUtils.isEmpty(country.getCountryName())) {
      criteria.andCountryNameLike("%" +  country.getCountryName() + "%");
    }
    if (!StringUtils.isEmpty(country.getCountryCode())) {
      criteria.andCountryCodeLike("%" + country.getCountryCode() + "%");
    }
    if (country.getId() != null) {
      criteria.andIdEqualTo(country.getId());
    }
    //分页查询
    PageHelper.startPage(page, rows);
    return selectByExample((Example)example);
  }

}
