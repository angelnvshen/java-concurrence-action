package own.spring.core.transcation.propagation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import own.spring.core.transcation.propagation.dao.CountryDao;
import own.spring.core.transcation.propagation.model.Country;

@Service
public class CountryService {

  @Autowired
  private CountryDao countryDao;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void updateCountry(int id) {

    countryDao.updateCountry(id);
  }

  @Transactional
  public void updateCountrySame(int id) {

    countryDao.updateCountry(id);

  }

  @Transactional(propagation = Propagation.NEVER)
  public void updateCountryNoTransaction(int id) {

    countryDao.updateCountry(id);

  }

  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public void selectById(int id) {

    Country country = countryDao.selectById(id);
    System.out.println(country);
  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void selectByIdSame(int id) {

    Country country = countryDao.selectById(id);
    System.out.println(country);
  }
}
