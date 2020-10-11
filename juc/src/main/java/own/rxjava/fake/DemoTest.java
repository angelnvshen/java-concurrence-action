package own.rxjava.fake;

public class DemoTest {

    public static void main(String[] args) {
        Calling calling = Caller.create(new Caller.OnCall<String>() {
            @Override
            public void call(Receiver receiver) {
                if (!receiver.isUnCalled()) {
                    receiver.onReceiver("test");
                    receiver.onCompleted();
                }
            }
        }).onCall(new Receiver<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onReceiver(String s) {
                System.out.println("onReceiver: " + s);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }
}
