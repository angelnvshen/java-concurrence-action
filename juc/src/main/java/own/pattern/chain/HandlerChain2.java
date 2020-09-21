package own.pattern.chain;

import java.util.ArrayList;
import java.util.List;

public class HandlerChain2 {
    private List<Handler> handlers = new ArrayList<>();

    public void addHandler(Handler handler) {
        this.handlers.add(handler);
    }

    public void handle() {
        for (Handler handler : handlers) {
            if (handler.doHandle()) { // doHandle 具体逻辑，可以删除 handle方法，这里为了方便
                break;
            }
        }
    }
}
