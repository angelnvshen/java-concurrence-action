package own.pattern.observer.normal;

public class User implements Observer<String> {

    private String name;

    private String message;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    private void read() {

        System.out.println(name + " received message : " + message);
    }
}
