package own.spring.core.model.factory;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by CHANEL on 2019/7/11.
 */
@Component
public class Phone {

    private String name;

    @Autowired
    ObjectFactory<Screen> objectFactory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Screen getScreen(){
        return objectFactory.getObject();
    }
}
