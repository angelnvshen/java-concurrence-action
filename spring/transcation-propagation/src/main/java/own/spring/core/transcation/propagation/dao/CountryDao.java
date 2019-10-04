package own.spring.core.transcation.propagation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import own.spring.core.transcation.propagation.model.Country;

@Repository
public class CountryDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  String sql = "update country set country_code = concat(country_code, 1) where id = ?";
  String sql2 = "update hashtest set word = concat(word, 1) where id = ?";

  public void updateCountry(int id) {
    jdbcTemplate.update(sql, id);
  }

  public void updateHash(int id) {
    jdbcTemplate.update(sql2, id);
  }

  public Country selectById(int id) {

    Country country = jdbcTemplate.query("select * from country where id = ?", (resultSet) -> {
      if (!resultSet.first()) {
        return null;
      }
      Country temp = new Country();
      temp.setId(resultSet.getInt(1));
      temp.setCountryCode(resultSet.getString(2));
      temp.setCountryCode(resultSet.getString(3));
      return temp;
    }, id);
    return country;
  }
}
