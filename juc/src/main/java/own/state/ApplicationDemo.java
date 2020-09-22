package own.state;

public class ApplicationDemo {
    public static void main(String[] args) {
//        MarioStateMachine mario = new MarioStateMachine();
//        MarioStateMachine2 mario = new MarioStateMachine2();
        MarioStateMachine3 mario = new MarioStateMachine3();
        mario.obtainMushRoom();
        int score = mario.getScore();
        State state = mario.getCurrentState();
        System.out.println("mario score: " + score + "; state: " + state);
    }
}