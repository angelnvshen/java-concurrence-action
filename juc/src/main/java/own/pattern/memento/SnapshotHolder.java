package own.pattern.memento;

import java.util.Stack;

public class SnapshotHolder {

    Stack<InputText> stack = new Stack<>();

    public InputText popSnapshot(){
        if(stack.isEmpty()) throw new RuntimeException("no element");
        return stack.pop();
    }

    public void pushSnapshot(InputText text) {
        InputText deepClonedInputText = new InputText();
        deepClonedInputText.setText(text.getText());
        stack.push(deepClonedInputText);
    }
}
