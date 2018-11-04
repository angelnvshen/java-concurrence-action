package own.stu.jkd8.interfaceDefault.parentClassFirst;

public interface IHello {

  default String getName(){
    return "I-HELLO";
  }
}
