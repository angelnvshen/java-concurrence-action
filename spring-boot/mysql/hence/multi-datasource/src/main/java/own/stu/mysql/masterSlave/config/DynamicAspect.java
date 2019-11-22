package own.stu.mysql.masterSlave.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Order(1)
@Component
@Aspect
public class DynamicAspect {

    @Before(value = "execution(public * own.stu.mysql.masterSlave..*Controller.*(..))")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        DynamicDB annotation = method.getAnnotation(DynamicDB.class);

        Class<?> declaringClass = method.getDeclaringClass();
        DynamicDB classAnnotation = declaringClass.getAnnotation(DynamicDB.class);
        if (classAnnotation != null) {
            DynamicDataSource.DBUtils.setDB(classAnnotation.value());
        } else {
            DynamicDataSource.DBUtils.setDefaultDB();
        }
    }
}
