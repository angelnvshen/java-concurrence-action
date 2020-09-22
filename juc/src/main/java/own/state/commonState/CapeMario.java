package own.state.commonState;

import own.state.IMario;
import own.state.State;

public class CapeMario implements IMario {

    private MarioStateMachine3 stateMachine;

    public CapeMario(MarioStateMachine3 stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public State getName() {
        return State.CAPE;
    }

    @Override
    public void obtainMushRoom() {
        // do nothing...
    }

    @Override
    public void obtainCape() {
        // do nothing...
    }

    @Override
    public void obtainFireFlower() {
        // do nothing...
    }

    @Override
    public void meetMonster() {
        stateMachine.setCurrentState(new SmallMario(stateMachine));
        stateMachine.setScore(stateMachine.getScore() - 300);
    }
}
