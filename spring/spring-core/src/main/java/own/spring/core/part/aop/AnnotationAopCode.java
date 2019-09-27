package own.spring.core.part.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AnnotationAopCode {

  @Pointcut("execution(* own.spring.core.part.aop.service.HelloWorld.*(..))")
  public void pointcut() {
  }

  @Before("pointcut()")
  public void before() {
    System.out.println("AnnotationAopCode`s before");
  }
}