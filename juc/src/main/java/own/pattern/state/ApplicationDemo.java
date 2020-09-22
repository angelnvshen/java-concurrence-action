package own.pattern.state;

import own.pattern.state.singletonCommonState.MarioStateMachine4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ApplicationDemo {
    public static void main1(String[] args) {
//        MarioStateMachine mario = new MarioStateMachine();
//        MarioStateMachine2 mario = new MarioStateMachine2();
//        MarioStateMachine3 mario = new MarioStateMachine3();
        MarioStateMachine4 mario = new MarioStateMachine4();
        mario.obtainMushRoom();
        int score = mario.getScore();
        State state = mario.getCurrentState();
        System.out.println("mario score: " + score + "; state: " + state);
    }

    public static void main(String[] args) {
//        List<String> names = new ArrayList<>();
        List<String> names = new LinkedList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        Iterator<String> iterator1 = names.iterator();
        Iterator<String> iterator2 = names.iterator();
        System.out.println(iterator1.next());
        iterator1.remove();
        System.out.println(iterator2.next()); // 运行结果？
    }
}