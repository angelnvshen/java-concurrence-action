package own.spring.core.java.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.core.ResolvableType;
import org.springframework.util.ReflectionUtils;

public class SpringResolvableTypeGenericClass {

  private List<String> listString;
  private List<List<String>> listLists;
  private Map<String, Long> maps;
  private Parent<String> parent;

  public Map<String, Long> getMaps() {
    return maps;
  }

  @Test
  public void doTestFindParent() {
    Field parent = ReflectionUtils.findField(SpringResolvableTypeGenericClass.class, "parent");
    ResolvableType parentResolvableType = ResolvableType.forField(parent);
    System.out.println(parentResolvableType.getType());

    //获取第0个位置的参数泛型
    System.out.println(parentResolvableType.getGeneric(0).resolve());
  }

  @Test
  public void doTestFindListStr() {
    Field listString = ReflectionUtils.findField(SpringResolvableTypeGenericClass.class, "listString");
    ResolvableType resolvableType = ResolvableType.forField(listString);
    System.out.println(resolvableType.getType());

    //获取第0个位置的参数泛型
    System.out.println(resolvableType.resolveGeneric(0));
  }

  @Test
  public void doTestFindlistLists() {
    Field listLists = ReflectionUtils.findField(SpringResolvableTypeGenericClass.class, "listLists");
    ResolvableType resolvableType = ResolvableType.forField(listLists);
    System.out.println(resolvableType.getType());

    //获取第0个位置的参数泛型
    System.out.println(resolvableType.resolveGeneric(0));

    //这两种实现方式一样的 泛型参数为：class java.lang.String
    System.out.println(resolvableType.getGeneric(0, 0).resolve());
    System.out.println(resolvableType.getGeneric(0).getGeneric(0).resolve());
  }

  @Test
  public void doTestFindMaps() {
    Field maps = ReflectionUtils.findField(SpringResolvableTypeGenericClass.class, "maps");
    ResolvableType resolvableType = ResolvableType.forField(maps);
    System.out.println(resolvableType.getType());

    System.out.println(resolvableType.getGeneric(0).resolve());
    System.out.println(resolvableType.getGeneric(1).resolve());
  }

  @Test
  public void doTestFindReturn() {
    Method getMaps = ReflectionUtils.findMethod(SpringResolvableTypeGenericClass.class, "getMaps");
    ResolvableType resolvableType = ResolvableType.forMethodReturnType(getMaps);
    System.out.println(resolvableType.getType());

    System.out.println(resolvableType.getGeneric(0).resolve());
    System.out.println(resolvableType.getGeneric(1).resolve());
  }
/**
 * 总结一句话就是使用起来非常的简单方便，更多超级复杂的可以参考spring 源码中的测试用例：ResolvableTypeTests
 * 其实这些的使用都是在Java的基础上进行使用的哦！
 Type是Java 编程语言中所有类型的公共高级接口（官方解释），也就是Java中所有类型的“爹”；其中，“所有类型”的描述尤为值得关注。它并不是我们平常工作中经常使用的 int、String、List、Map等数据类型，
 而是从Java语言角度来说，对基本类型、引用类型向上的抽象；
 Type体系中类型的包括：原始类型(Class)、参数化类型(ParameterizedType)、数组类型(GenericArrayType)、类型变量(TypeVariable)、基本类型(Class);
 原始类型，不仅仅包含我们平常所指的类，还包括枚举、数组、注解等；
 参数化类型，就是我们平常所用到的泛型List、Map；
 数组类型，并不是我们工作中所使用的数组String[] 、byte[]，而是带有泛型的数组，即T[] ；
 基本类型，也就是我们所说的java的基本类型，即int,float,double等
 */
}