package own.spring.core.transcation.propagation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import own.spring.core.transcation.propagation.dao.CountryDao;

@Service
public class MultiService {

  @Autowired
  private CountryService countryService;

  @Autowired
  private HashService hashService;

  @Transactional
  public void updateMulti() {

    countryService.updateCountry(1);
    hashService.updateHash(7);

    int i = 100 / 0;
  }

  @Transactional
  public void updateMultiSame() {

    countryService.updateCountry(1);
    countryService.updateCountrySame(2);
  }

  @Transactional
  public void updateMultiNoTransaction() {

    countryService.updateCountry(1);
    countryService.updateCountryNoTransaction(2);
  }

  @Transactional
  public void updateMultiNotSupport() {

    countryService.updateCountrySame(1);
    countryService.selectById(1);
  }

  @Transactional
  public void updateMultiSupport() {

    countryService.updateCountrySame(1);
    countryService.selectByIdSame(1);
  }
}
