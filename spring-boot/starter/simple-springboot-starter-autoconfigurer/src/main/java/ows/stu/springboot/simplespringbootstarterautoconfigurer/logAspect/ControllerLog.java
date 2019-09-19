package ows.stu.springboot.simplespringbootstarterautoconfigurer.logAspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerLog {

  /**
   * 何种场景下的通用日志打印
   */
  String module();
}
