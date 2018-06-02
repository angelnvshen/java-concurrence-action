package own.stu.java.concurrence.action.chapter_3;

/**
 * Created by CHANEL on 2018/5/14.
 */
public class ThisEscape {

    private String name = null;
    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething(e);
            }
        });

        name = "test";
    }

    void doSomething(Event e){

    }

    public static void main(String[] args) {

        EventSource source = new EventSource() {
            @Override
            public void registerListener(EventListener listener) {
                listener.onEvent(null);
            }
        };

        ThisEscape escape = new ThisEscape(source);
        System.out.println(escape.name);
    }

    interface EventSource{
        void registerListener(EventListener listener);
    }

    interface EventListener{
        void onEvent(Event e);
    }

    interface Event{

    }
}
