package own.state.commonState;

import own.state.IMario;
import own.state.State;

//状态模式通过将事件触发的状态转移和动作执行，拆分到不同的状态类中，来避免分支判断
//逻辑。
public class MarioStateMachine3 {

    private int score;
    private IMario currentState;

    public MarioStateMachine3() {
        this.score = 0;
        this.currentState = new SmallMario(this);
    }

    public void obtainMushRoom() {
        this.currentState.obtainMushRoom();
    }

    public void obtainCape() {
        this.currentState.obtainCape();
    }

    public void obtainFireFlower() {
        this.currentState.obtainFireFlower();
    }

    public void meetMonster() {
        this.currentState.meetMonster();
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
