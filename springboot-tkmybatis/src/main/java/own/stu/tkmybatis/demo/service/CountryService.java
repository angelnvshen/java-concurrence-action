package own.stu.tkmybatis.demo.service;

import java.util.List;
import own.stu.tkmybatis.demo.common.service.BaseService;
import own.stu.tkmybatis.demo.model.Country;

public interface CountryService extends BaseService<Country> {
  List<Country> findAll();
  List<Country> selectByCountry(Country country, int page, int rows);
}
