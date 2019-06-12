package own.stu.spring.springsource.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by CHANEL on 2019/6/12.
 */
public class MyImportSelector implements ImportSelector {

    // annotationMetadata 引用 myImportSelector 的类的所有的注解
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"own.stu.spring.springsource.model.RainBow"};
    }
}
