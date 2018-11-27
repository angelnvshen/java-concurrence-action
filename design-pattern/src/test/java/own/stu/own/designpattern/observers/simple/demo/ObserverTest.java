package own.stu.own.designpattern.observers.simple.demo;

import org.junit.Test;

public class ObserverTest {

  @Test
  public void test(){
    User user1 = new User("QIN");
    User user2 = new User("PIN");
    User user3 = new User("YIN");

    WechatServer server = new WechatServer();
    server.register(user1);
    server.register(user2);
    server.register(user3);

    server.publishMessage("PHP是世界上最好用的语言！");
    System.out.println("----------------------------------------------");

    server.remove(user2);
    server.publishMessage("JAVA是世界上最好用的语言！");

  }
}
