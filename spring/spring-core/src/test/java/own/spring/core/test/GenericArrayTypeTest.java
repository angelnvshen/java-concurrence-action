package own.spring.core.test;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * 描述的是形如：A< T>[]或T[]类型变量和原始类型
 * @param <T>
 */
public class GenericArrayTypeTest<T> {

  /**
   * 含有泛型数组的才是GenericArrayType
   *
   * @param pTypeArray GenericArrayType type :java.util.List<java.lang.String>[]; genericComponentType:java.util.List<java.lang.String>
   * @param vTypeArray GenericArrayType type :T[];genericComponentType:T
   * @param list ParameterizedType type :java.util.List<java.lang.String>;
   * @param strings type :class [Ljava.lang.String;
   * @param test type :class [Lcom.wangji.demo.GenericArrayTypeTest;
   */
  public void testGenericArrayType(List<String>[] pTypeArray, T[] vTypeArray
      , List<String> list, String[] strings, GenericArrayTypeTest[] test) {
  }

  @Test
  public void test() {
    Arrays.asList(getClass().getDeclaredMethods()).forEach(method -> {
      if (!method.getName().equals("testGenericArrayType")) {
        return;
      }
      System.out.println("methodName : " + method.getName());
      Arrays.asList(method.getGenericParameterTypes()).forEach(type -> {
        System.out.println("typeName : " + type.getTypeName());
        if (type instanceof ParameterizedType) {
          System.out.println("ParameterizedType type :" + type);
        } else if (type instanceof GenericArrayType) {
          System.out.println("GenericArrayType type :" + type);
          Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
          /**
           * 获取泛型数组中元素的类型，要注意的是：无论从左向右有几个[]并列，
           * 这个方法仅仅脱去最右边的[]之后剩下的内容就作为这个方法的返回值。
           */
          System.out.println("genericComponentType:" + genericComponentType);
        } else if (type instanceof WildcardType) {
          System.out.println("WildcardType type :" + type);
        } else if (type instanceof TypeVariable) {
          System.out.println("TypeVariable type :" + type);
        } else {
          System.out.println("type :" + type);
        }
        System.out.println(" ------------------ ");
      });
    });
  }

}