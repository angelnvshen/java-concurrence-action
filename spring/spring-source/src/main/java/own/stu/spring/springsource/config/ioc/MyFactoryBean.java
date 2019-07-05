package own.stu.spring.springsource.config.ioc;

import org.springframework.beans.factory.FactoryBean;
import own.stu.spring.springsource.model.Color;

/**
 * Created by CHANEL on 2019/6/12.
 */
public class MyFactoryBean implements FactoryBean<Color> {
    @Override
    public Color getObject() throws Exception {
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
