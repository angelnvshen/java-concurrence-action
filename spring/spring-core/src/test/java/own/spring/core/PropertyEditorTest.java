package own.spring.core;

import com.sun.beans.editors.IntegerEditor;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.datetime.DateFormatter;
import own.spring.core.model.Order;
import own.spring.core.propertyEditor.CustomDateEditor;
import own.spring.core.propertyEditor.UUIDEditor;

public class PropertyEditorTest {

  @Test
  public void testUUIDEditor() {

    //UUID对象转化为字符串
    UUIDEditor uuidEditor = new UUIDEditor();
    uuidEditor.setValue(UUID.randomUUID());
    System.out.println(uuidEditor.getAsText());

    //字符串转化为UUID
    UUIDEditor editor_2 = new UUIDEditor();
    editor_2.setAsText("2-1-1-2-3");
    System.out.println(editor_2.getAsText());
    System.out.println(editor_2.getValue().getClass());
  }

  @Test
  public void testIntegerEditor(){
    IntegerEditor integerEditor = new IntegerEditor();
    integerEditor.setValue(23456);
    System.out.println(integerEditor.getAsText());
  }

  @Test
  public void testDateFormatter() throws ParseException {

    DateFormatter dateFormatter = new DateFormatter();
    String print = dateFormatter.print(new Date(), Locale.getDefault());
    System.out.println(print);

    Date parse = dateFormatter.parse("2019-07-18", Locale.getDefault());
    System.out.println(parse);
  }

  @Test
  public void testCustomerEditor(){
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/BeanConfig.xml");
    Order order = applicationContext.getBean("order", Order.class);
    System.out.println(order);

    /*CustomDateEditor customDateEditor = new CustomDateEditor("yyyy-MM-dd HH:mm:ss");
    customDateEditor.setValue(new Date());
    System.out.println(customDateEditor.getAsText());

    customDateEditor.setAsText("2019-07-18 23:32:15");
    System.out.println(customDateEditor.getAsText());*/
  }

}
