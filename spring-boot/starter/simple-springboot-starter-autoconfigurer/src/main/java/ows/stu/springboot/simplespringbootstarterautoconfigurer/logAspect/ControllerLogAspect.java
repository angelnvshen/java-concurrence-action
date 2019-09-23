package ows.stu.springboot.simplespringbootstarterautoconfigurer.logAspect;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class ControllerLogAspect {

  /**
   * 定义切入点：对要拦截的方法进行定义与限制，如包、类
   *
   * 1、execution(public * *(..)) 任意的公共方法 2、execution（* set*（..）） 以set开头的所有的方法 3、execution（*
   * com.lingyejun.annotation.LoggerApply.*（..））com.lingyejun.annotation.LoggerApply这个类里的所有的方法 4、execution（*
   * com.lingyejun.annotation.*.*（..））com.lingyejun.annotation包下的所有的类的所有的方法 5、execution（*
   * com.lingyejun.annotation..*.*（..））com.lingyejun.annotation包及子包下所有的类的所有的方法 6、execution(*
   * com.lingyejun.annotation..*.*(String,?,Long)) com.lingyejun.annotation包及子包下所有的类的有三个参数，第一个参数为String类型，第二个参数为任意类型，第三个参数为Long类型的方法
   * 7、execution(@annotation(com.lingyejun.annotation.Lingyejun))
   */
  @Pointcut("@annotation(ows.stu.springboot.simplespringbootstarterautoconfigurer.logAspect.ControllerLog)")
  private void cutMethod() {

  }

  @Around("cutMethod()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    // 获取目标方法的名称
    String methodName = joinPoint.getSignature().getName();

    // 获取方法传入参数
    Object[] params = joinPoint.getArgs();

    ControllerLog log = getDeclaredAnnotation(joinPoint);
    System.out.println("==@Around== logger --》 method name " + methodName + " args " + params[0]);
    // 执行源方法
    Object proceed = joinPoint.proceed();
    // 模拟进行验证
    System.out.println("==@Around== logger --》 " + log.module());
    return proceed;
  }

  /**
   * 获取方法中声明的注解
   */
  private ControllerLog getDeclaredAnnotation(ProceedingJoinPoint joinPoint) throws Exception {
    // 获取方法名
    String methodName = joinPoint.getSignature().getName();
    // 反射获取目标类
    Class<?> targetClass = joinPoint.getTarget().getClass();
    // 拿到方法对应的参数类型
    Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
    // 根据类、方法、参数类型（重载）获取到方法的具体信息
    Method objMethod = targetClass.getMethod(methodName, parameterTypes);
    // 拿到方法定义的注解信息
    ControllerLog annotation = objMethod.getAnnotation(ControllerLog.class);
    return annotation;
  }
}