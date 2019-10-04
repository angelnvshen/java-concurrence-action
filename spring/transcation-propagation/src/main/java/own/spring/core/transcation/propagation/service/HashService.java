package own.spring.core.transcation.propagation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import own.spring.core.transcation.propagation.dao.CountryDao;

@Service
public class HashService {

  @Autowired
  private CountryDao countryDao;

  @Transactional
  public void updateHash(int id) {

    countryDao.updateHash(id);
  }
}
