package own.pattern.state.commonState;

import own.pattern.state.IMario;
import own.pattern.state.State;

public class SuperMario implements IMario {

    private MarioStateMachine3 stateMachine;

    public SuperMario(MarioStateMachine3 stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public State getName() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRoom() {
        // do nothing...
    }

    @Override
    public void obtainCape() {
        stateMachine.setCurrentState(new CapeMario(stateMachine));
        stateMachine.setScore(stateMachine.getScore() + 200);
    }

    @Override
    public void obtainFireFlower() {
        stateMachine.setCurrentState(new FireMario(stateMachine));
        stateMachine.setScore(stateMachine.getScore() + 300);
    }

    @Override
    public void meetMonster() {
        stateMachine.setCurrentState(new SmallMario(stateMachine));
        stateMachine.setScore(stateMachine.getScore() - 100);
    }
}
