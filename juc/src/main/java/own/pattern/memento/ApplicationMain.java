package own.pattern.memento;

import java.util.Scanner;

public class ApplicationMain {
    public static void main(String[] args) {
        InputText text = new InputText();
        SnapshotHolder holder = new SnapshotHolder();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals(":list")) {
                System.out.println(text.getText());
            } else if (input.equals(":undo")) {
                Snapshot snapshot = holder.popSnapshot();
                text.restoreSnapshot(snapshot);
            } else {
                holder.pushSnapshot(text.createSnapshot());
                text.append(input);
            }
        }
    }
}