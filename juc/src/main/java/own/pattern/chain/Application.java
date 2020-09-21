package own.pattern.chain;

// 使用举例
public class Application {
    public static void main(String[] args) {
//        HandlerChain chain = new HandlerChain();
        HandlerChain2 chain = new HandlerChain2();
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());
        chain.handle();
    }
}