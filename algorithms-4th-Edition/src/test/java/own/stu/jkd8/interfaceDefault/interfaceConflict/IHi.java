package own.stu.jkd8.interfaceDefault.interfaceConflict;

public interface IHi {

  default String getName() {
    return "I-HELLO";
  }
}
