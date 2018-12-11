package own.stu.own.designpattern.strategy.simple.demo;

import lombok.Data;

@Data
public class Context {

  Weapon weapon;

  public Context(Weapon weapon) {
    this.weapon = weapon;
  }

  public void gun(){
    weapon.fire();
  }
}
