package own.spring.core.test;

import static own.spring.core.common.Util.printArrays;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import org.junit.Test;
import org.springframework.core.ResolvableType;

public class Children extends Parent<String> implements IParent<Long> {

  /**
   * 这里是获取父类中泛型，如果有多个也是一样的方式哈哈！获取到的泛型参数还可能是 通配符表达式， 这里也是可以处理的，多个判断而已
   */
  @Test
  public void test() {
    Type genericSuperclass = Children.class.getGenericSuperclass();
    if (genericSuperclass instanceof ParameterizedType) {
      System.out.println(genericSuperclass.getTypeName());
      printArrays(((ParameterizedType) genericSuperclass).getActualTypeArguments());
    }
  }

  /**
   * 这里获取父接口中的泛型参数
   */
  @Test
  public void test2() {
    Arrays.asList(Children.class.getGenericInterfaces()).forEach(type -> {
      if (type instanceof ParameterizedType) {
        printArrays(((ParameterizedType) type).getActualTypeArguments());
      }
    });
  }

  @Test
  public void test3() {

    ResolvableType superType = ResolvableType.forClass(Children.class).getSuperType();
    System.out.println(superType.resolveGenerics()[0]);

    System.out.println(ResolvableType.forClass(Children.class).getInterfaces()[0].resolveGenerics()[0]);
  }
}