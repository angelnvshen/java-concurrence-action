package own.state;

import own.state.commonState.MarioStateMachine3;
import own.state.singletonCommonState.MarioStateMachine4;

public class ApplicationDemo {
    public static void main(String[] args) {
//        MarioStateMachine mario = new MarioStateMachine();
//        MarioStateMachine2 mario = new MarioStateMachine2();
//        MarioStateMachine3 mario = new MarioStateMachine3();
        MarioStateMachine4 mario = new MarioStateMachine4();
        mario.obtainMushRoom();
        int score = mario.getScore();
        State state = mario.getCurrentState();
        System.out.println("mario score: " + score + "; state: " + state);
    }
}