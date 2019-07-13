package own.spring.core.model.factory;

import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by CHANEL on 2019/7/11.
 */
@Component("myObjectFactoryCreatingFactoryBean")
public class MyObjectFactoryCreatingFactoryBean extends ObjectFactoryCreatingFactoryBean {

    @Override
    public void setTargetBeanName(String targetBeanName) {
        super.setTargetBeanName(targetBeanName);
    }
}
