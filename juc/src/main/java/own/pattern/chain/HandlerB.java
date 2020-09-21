package own.pattern.chain;

public class HandlerB extends Handler {
    @Override
    public void handle() {

        boolean handle = false;

        //...
        if (!handle && successor != null) {
            successor.handle();
        }
    }
}
