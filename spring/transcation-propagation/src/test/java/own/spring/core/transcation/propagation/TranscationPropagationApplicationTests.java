package own.spring.core.transcation.propagation;

import com.mysql.cj.xdevapi.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import own.spring.core.transcation.propagation.service.CountryManualService;
import own.spring.core.transcation.propagation.service.MultiService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TranscationPropagationApplicationTests {

  @Autowired
  private MultiService multiService;

  @Autowired
  private CountryManualService countryManualService;

  @Test
  public void contextLoads() {
  }

  @Test
  public void test() {
//    multiService.updateMulti();
//    multiService.updateMultiSame();
//    multiService.updateMultiNoTransaction();
//    multiService.updateMultiNotSupport();
    multiService.updateMultiSupport();
  }

  @Test
  public void testTransactionManual() {
//    countryManualService.updateCountry(1);
    countryManualService.updateCountryManualProgramming(1);
  }

}
