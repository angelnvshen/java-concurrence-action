package own.stu.own.designpattern.observers.simple.demo;

public interface Observable {

  void register(Observer observer);

  void remove(Observer observer);

  void notifyObserve();
}
