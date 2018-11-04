package own.stu.jkd8.interfaceDefault.interfaceConflict;

import own.stu.jkd8.interfaceDefault.parentClassFirst.IHello;

public class Sub2Class implements IHello, IHi {

  @Override
  public String getName() {
    return IHello.super.getName();
  }
}
