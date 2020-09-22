package own.pattern.memento;

import java.util.Stack;

public class SnapshotHolder {

    Stack<Snapshot> stack = new Stack<>();

    public Snapshot popSnapshot(){
        if(stack.isEmpty()) throw new RuntimeException("no element");
        return stack.pop();
    }

    public void pushSnapshot(Snapshot snapshot) {
        stack.push(snapshot);
    }
}
