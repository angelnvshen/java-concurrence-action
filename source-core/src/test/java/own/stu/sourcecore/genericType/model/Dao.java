package own.stu.sourcecore.genericType.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Dao<T> {

  Class<T> beanClass;

  public Dao() {
    System.out.println("Dao : "+ this);
    ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
    Type[] types = type.getActualTypeArguments();
    if(types != null || types.length > 0){
      beanClass = (Class<T>) types[0];
      System.out.println("dao<T> ：" + beanClass.getName());
      System.out.println("superClass : " + this.getClass().getSuperclass().getName());
    }
  }
}
