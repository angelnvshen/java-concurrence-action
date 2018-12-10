package own.stu.com.plugin.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})//ElementType.TYPE表示可以用在类上，ElementType.METHOD表示可以用在方法上
@Retention(RetentionPolicy.RUNTIME)//RetentionPolicy.RUNTIME运行事保留RetentionPolicy.CLASS编译时保留
@Documented//会被 javadoc 之类的工具处理
@Inherited//表示可以被继承
public @interface StatisticsTime {
 
}