package own.stu.sourcecore.genericType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.junit.Test;
import own.stu.sourcecore.genericType.model.Dao;
import own.stu.sourcecore.genericType.model.Student;
import own.stu.sourcecore.genericType.model.StudentDao;

public class GenericTypeTest {

  @Test
  public void test(){
    System.out.println("123456789012345678901234567890123456789012345678901234567890".length());
  }

  @Test
  public void testGetSupperClass(){

    Student student = new Student();
    Class superClass = student.getClass().getSuperclass();
    System.out.println(superClass.getName());
  }

  @Test
  public void testGetGenericSuperClass(){
    StudentDao dao = new StudentDao();
    Type type = dao.getClass().getGenericSuperclass();
    System.out.println(type);
  }

  @Test
  public void testGetGenericSuperClass2(){
    Student object = newUsr(StudentDao.class);
    System.out.println(object);
  }

  @Test
  public void testGetGenericSuperClass3(){
    // java.lang.Class cannot be cast to java.lang.reflect.ParameterizedType
    Dao<Student> dao = new Dao<>();
    Student object = newUsr(dao.getClass());
    System.out.println(object);
  }

  public static <D> D newUsr(Class beanClass) {
    D newUsr;
    try {
      // 通过反射获取model的真实类型
      ParameterizedType pt = (ParameterizedType) beanClass.getGenericSuperclass();
      Class<D> clazz = (Class<D>) pt.getActualTypeArguments()[0];
      // 通过反射创建model的实例
      newUsr = clazz.newInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return newUsr;
  }
}