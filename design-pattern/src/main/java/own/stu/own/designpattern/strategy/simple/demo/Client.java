package own.stu.own.designpattern.strategy.simple.demo;

public class Client {

  public static void main(String[] args) {
    Context context = new Context(new AWP());
    context.gun();

    System.out.println(" ..... ");

    context.setWeapon(new ShotGun());
    context.gun();
  }
}
