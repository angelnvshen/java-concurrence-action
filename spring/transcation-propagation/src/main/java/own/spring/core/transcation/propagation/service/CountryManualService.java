package own.spring.core.transcation.propagation.service;

import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRED;
import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRES_NEW;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import own.spring.core.transcation.propagation.dao.CountryDao;

@Service
public class CountryManualService {

  @Autowired
  private CountryDao countryDao;

  @Autowired
  TransactionTemplate transactionTemplate;

  @Autowired
  private DataSourceTransactionManager dataSourceTransactionManager;

  public void updateCountry(int id) {
    transactionTemplate.execute(new TransactionCallbackWithoutResult() {

      @Override
      protected void doInTransactionWithoutResult(TransactionStatus status) {
        countryDao.updateCountry(id);
        try {
          int i = 100 / 0;
        } catch (Exception e) {
          e.printStackTrace();
          status.setRollbackOnly();
        }
      }
    });
  }

  public void updateCountryManualProgramming(int id) {

    TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
    TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
    countryDao.updateCountry(id);

    TransactionDefinition transactionDefinition2 = new DefaultTransactionDefinition();
    ((DefaultTransactionDefinition) transactionDefinition2).setPropagationBehavior(PROPAGATION_REQUIRES_NEW);
//    ((DefaultTransactionDefinition) transactionDefinition2).setPropagationBehavior(PROPAGATION_REQUIRED);
    TransactionStatus transactionStatus2 = dataSourceTransactionManager.getTransaction(transactionDefinition2);
    countryDao.updateCountry(id + 1);
    try {
      int i = 100 / 0;
    } catch (Exception e) {
      e.printStackTrace();
      transactionStatus2.setRollbackOnly();
    }

    dataSourceTransactionManager.commit(transactionStatus2);

    dataSourceTransactionManager.commit(transactionStatus);
  }
}
