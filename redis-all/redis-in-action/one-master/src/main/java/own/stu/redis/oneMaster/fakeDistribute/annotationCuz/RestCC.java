package own.stu.redis.oneMaster.fakeDistribute.annotationCuz;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

// TODO
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@RequestMapping
public @interface RestCC {
}
