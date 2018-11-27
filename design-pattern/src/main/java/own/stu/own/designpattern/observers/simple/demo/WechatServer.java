package own.stu.own.designpattern.observers.simple.demo;

import java.util.ArrayList;
import java.util.List;

public class WechatServer implements Observable {

  private List<Observer> observerList = new ArrayList<>();
  private String message;

  @Override
  public void register(Observer observer) {
    observerList.add(observer);
  }

  @Override
  public void remove(Observer observer) {
    if(!observerList.isEmpty())
      observerList.remove(observer);
  }

  @Override
  public void notifyObserve() {
    for(Observer observer : observerList)
      observer.update(message);
  }

  public void publishMessage(String message){
    this.message = message;
    System.out.println("微信服务更新消息： " + message);
    notifyObserve();
  }

}
