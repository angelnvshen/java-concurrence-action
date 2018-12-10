package own.stu.com.plugin.demo.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class StatisticsTimeAspect {

  @Pointcut("@within(statisticsTime)")
  public void annotationPointcut(StatisticsTime statisticsTime) {

  }

  @Around("@within(statisticsTime)")
  public Object aroundAdvice(ProceedingJoinPoint joinPoint, StatisticsTime statisticsTime) throws Throwable {

    MethodSignature signature = (MethodSignature) joinPoint.getSignature();//通过这个对象取得本次请求的方法信息

    long start = System.currentTimeMillis();
    try {
      return joinPoint.proceed();
    } finally {
      long end = System.currentTimeMillis();
      log.info(String.format("invoke %s cost %d %s", signature.getName(), (end - start), "ms"));
    }
  }
}