package own.pattern.state.singletonCommonState;

import own.pattern.state.State;

//上面的代码还可以继续优化，我们可以将状态类设计成单例，毕竟状态类中不包含
//任何成员变量。
public class MarioStateMachine4 {

    private int score;
    private IMario currentState;

    public MarioStateMachine4() {
        this.score = 0;
        this.currentState = SmallMario.getInstance();
    }

    public void obtainMushRoom() {
        this.currentState.obtainMushRoom(this);
    }

    public void obtainCape() {
        this.currentState.obtainCape(this);
    }

    public void obtainFireFlower() {
        this.currentState.obtainFireFlower(this);
    }

    public void meetMonster() {
        this.currentState.meetMonster(this);
    }

    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState.getName();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCurrentState(IMario currentState) {
        this.currentState = currentState;
    }
}
