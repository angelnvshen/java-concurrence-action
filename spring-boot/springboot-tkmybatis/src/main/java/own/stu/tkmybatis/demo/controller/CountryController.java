package own.stu.tkmybatis.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import own.stu.tkmybatis.demo.model.Country;
import own.stu.tkmybatis.demo.service.impl.CountryServiceImpl;

@RestController
public class CountryController {

  @Autowired
  private CountryServiceImpl countryService;

  @GetMapping("getUser")
  public Country getUserById(Integer id){
    return countryService.selectByKey(id);
  }

  @GetMapping("findAll")
  public List<Country> findAll(){
    return countryService.findAll();
  }

  @GetMapping("getByPage")
  public List<Country> getByPage(
      @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize
  ){
    return countryService.selectPage(pageNum, pageSize);
  }

  /**
   * tk-mybatis 内置的 example 查询
   * */
  @GetMapping("getByExample")
  public List<Country> getByExample(
      @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
      Country country,
      @RequestParam(value = "callType", defaultValue = "1")String callType){
    if("1".equals(callType)) {
      return countryService.selectByCountry(country, pageNum, pageSize);
    }else if("2".equals(callType)) {
      return countryService.selectByCountryTwo(country, pageNum, pageSize);
    }else
      return countryService.selectByCountryThree(country, pageNum, pageSize);
  }
}
