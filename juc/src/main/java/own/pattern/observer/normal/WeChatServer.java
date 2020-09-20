package own.pattern.observer.normal;

import java.util.ArrayList;
import java.util.List;

public class WeChatServer implements Observable {

    private List<Observer> observerList = new ArrayList<>();

    private String message;

    @Override
    public void add(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    public void publish(String message) {
        this.message = message;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observerList) {
            o.update(message);
        }
    }

    public static void main(String[] args) {
        WeChatServer server = new WeChatServer();
        Observer u1 = new User("u1");
        Observer u2 = new User("u2");
        Observer u3 = new User("u3");
        Observer u4 = new User("u4");

        server.add(u1);
        server.add(u2);
        server.add(u3);
        server.add(u4);

        server.publish("heelo");
    }
}
