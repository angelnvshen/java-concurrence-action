package own.spring.core.reflect;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import org.junit.Test;

public class TypeVariableTest<T extends Number & Serializable, V> {

  /**
   * TypeVariable
   */
  private T key;

  /**
   * TypeVariable
   */
  private V value;

  /**
   * GenericArrayType V[]-> V TypeVariable 两种混合起来了
   */
  private V[] values;
  /**
   * 原始类型，不仅仅包含我们平常所指的类，还包括枚举、数组、注解等； 基本类型，也就是我们所说的java的基本类型，即int,float,double等
   */
  private String str;

  /**
   * 获取ParameterizedType List<T> -> T TypeVariable 两种混合起来了
   */
  private List<T> tList;

  @Test
  public void test() {
    Field f;
    Field[] fields = TypeVariableTest.class.getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
      f = fields[i];
      if (f.getName().equals("log")) {
        continue;
      }
      System.out.println("begin ******当前field:" + f.getName() + " *************************");
      if (f.getGenericType() instanceof ParameterizedType) {
        ParameterizedType parameterizedType = (ParameterizedType) f.getGenericType();
        for (Type type : parameterizedType.getActualTypeArguments()) {
          System.out.println(f.getName() + ": 获取ParameterizedType:" + type);
          if (type instanceof TypeVariable) {
            printTypeVariable(f.getName(), (TypeVariable) type);
          }
        }
        if (parameterizedType.getOwnerType() != null) {
          System.out.println(f.getName() + ":getOwnerType:" + parameterizedType.getOwnerType());
        } else {
          System.out.println(f.getName() + ":getOwnerType is null");
        }
        if (parameterizedType.getRawType() != null) {
          System.out.println(f.getName() + ":getRawType:" + parameterizedType.getRawType());
        }
      } else if (f.getGenericType() instanceof GenericArrayType) {
        GenericArrayType genericArrayType = (GenericArrayType) f.getGenericType();
        System.out.println("GenericArrayType type :" + genericArrayType);
        Type genericComponentType = genericArrayType.getGenericComponentType();
        if (genericComponentType instanceof TypeVariable) {
          TypeVariable typeVariable = (TypeVariable) genericComponentType;
          printTypeVariable(f.getName(), typeVariable);
        }
      } else if (f.getGenericType() instanceof TypeVariable) {
        TypeVariable typeVariable = (TypeVariable) f.getGenericType();
        printTypeVariable(f.getName(), typeVariable);
      } else {
        System.out.println("type :" + f.getGenericType());
      }
      System.out.println("end ******当前field:" + f.getName() + " *************************");
    }
  }

  /**
   * 1、Type[] getBounds() 类型对应的上限，默认为Object
   * 2、D getGenericDeclaration()  获取声明该类型变量实体，也就是TypeVariableTest<T>中的TypeVariableTest
   * 3、String getName() 获取类型变量在源码中定义的名称；
   */
  private static void printTypeVariable(String fieldName, TypeVariable typeVariable) {
    for (Type type : typeVariable.getBounds()) {
      System.out.println(fieldName + ": TypeVariable getBounds " + type);
    }
    System.out.println("定义Class getGenericDeclaration: " + typeVariable.getGenericDeclaration());
    System.out.println("getName: " + typeVariable.getName());
  }

}