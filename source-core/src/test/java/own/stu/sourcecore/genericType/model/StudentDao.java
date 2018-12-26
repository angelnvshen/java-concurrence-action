package own.stu.sourcecore.genericType.model;

public class StudentDao extends Dao<Student> {

  public StudentDao() {
    System.out.println("studentDao : " + this);
  }
}
