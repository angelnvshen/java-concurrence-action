package own.spring.core.model.factory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by CHANEL on 2019/7/11.
 */
@Scope("prototype")
@Component("screen")
public class Screen {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
