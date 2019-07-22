package own.spring.core;

import com.sun.beans.editors.IntegerEditor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.datetime.DateFormatter;
import own.spring.core.model.Book;
import own.spring.core.model.Order;
import own.spring.core.propertyEditor.UUIDEditor;

public class IntrospectorTest {

  // 通过反射机制操作name属性：
  @Test
  public void test_1() {

    Book book = new Book();
    try {
//      Field nameField = book.getClass().getField("name");
      Field nameField = book.getClass().getDeclaredField("name");
      nameField.setAccessible(true);
      nameField.set(book, "农夫");
      System.out.println(nameField.get(book));
      System.out.println(book.getName());
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  //通过内省操作name属性：
  @Test
  public void test_2() {

    try {

      Book book = new Book();

      PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", Book.class);

      Method writeMethod = propertyDescriptor.getWriteMethod();
      Object invoke = writeMethod.invoke(book, "擦泪");
      System.out.println(invoke);

      Method readMethod = propertyDescriptor.getReadMethod();
      Object invoke1 = readMethod.invoke(book, null);
      System.out.println(invoke1);

      //操作所有属性
      BeanInfo bi = Introspector.getBeanInfo(Book.class);
      PropertyDescriptor[] pds = bi.getPropertyDescriptors();
      for(PropertyDescriptor p : pds){

      }

    } catch (IntrospectionException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }
}

