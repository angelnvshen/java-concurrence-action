package own.stu.spring.springsource.config.aop.aspect;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspect {

  @Pointcut(value = "execution(public * own.stu.spring.*..MathCalculation.*(..))")
  public void pointcut() {
  }

  @Before(value = "execution(public * own.stu.spring.*..MathCalculation.*(..))")
  public void before(JoinPoint joinPoint) {
    System.out.println(joinPoint.getSignature().getName() +
        " before ... { " + Arrays.asList(joinPoint.getArgs()) + "}");
  }

  @After(value = "pointcut()")
  public void after(JoinPoint joinPoint) {
    System.out.println(joinPoint.getSignature().getName() + "after ... {}");
  }

  @AfterReturning(value = "own.stu.spring.springsource.config.aop.aspect.LogAspect.pointcut()", returning = "result")
  public void afterReturn(JoinPoint joinPoint, Object result) {
    System.out.println(joinPoint.getSignature().getName() + " afterReturn ... {" + result + "}");
  }

  @AfterThrowing(value = "pointcut()", throwing = "exception")
  public void afterThrowing(JoinPoint joinPoint, Exception exception) {
    System.out.println(joinPoint.getSignature().getName() + " afterThrowing ... {" + exception + "}");
  }
}