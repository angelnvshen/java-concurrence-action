package own.spring.core;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.convert.TypeDescriptor;
import own.spring.core.model.Book;
import own.spring.core.model.inject.Cup;
import own.spring.core.model.inject.Water;

public class BeanWrapperTest {

  @Test
  public void test_simple_bean() {

    Book book = new Book();
    BeanWrapper beanWrapper = new BeanWrapperImpl(book);
    beanWrapper.setPropertyValue("name", "杭州");
    System.out.println(beanWrapper.getPropertyValue("name"));
  }

  @Test
  public void test_complex_bean() {

    Cup cup = new Cup();
    BeanWrapper beanWrapper = new BeanWrapperImpl(cup);
    beanWrapper.setPropertyValue("water", new Water());
    beanWrapper.setPropertyValue("water.name", "pureWater");
    System.out.println(beanWrapper.getPropertyValue("water.name"));

    System.out.println(beanWrapper.isReadableProperty("water"));
    System.out.println(beanWrapper.isWritableProperty("water"));
    Class<?> water = beanWrapper.getPropertyType("water");
    System.out.println(water);
    PropertyDescriptor propertyDescriptor = beanWrapper.getPropertyDescriptor("water");
    System.out.println(propertyDescriptor.getPropertyEditorClass());
    TypeDescriptor typeDescriptor = beanWrapper.getPropertyTypeDescriptor("water");
    System.out.println(typeDescriptor.getType());

    PropertyEditor customEditor = beanWrapper.findCustomEditor(Water.class, "water");
    System.out.println(customEditor);

  }
}
