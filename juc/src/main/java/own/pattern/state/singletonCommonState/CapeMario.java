package own.pattern.state.singletonCommonState;

import own.pattern.state.State;

public class CapeMario implements IMario {

    private static final CapeMario instance = new CapeMario();

    private CapeMario() {
    }

    public static CapeMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.CAPE;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine4 stateMachine) {
        // do nothing...
    }

    @Override
    public void obtainCape(MarioStateMachine4 stateMachine) {
        // do nothing...
    }

    @Override
    public void obtainFireFlower(MarioStateMachine4 stateMachine) {
        // do nothing...
    }

    @Override
    public void meetMonster(MarioStateMachine4 stateMachine) {
        stateMachine.setCurrentState(SmallMario.getInstance());
        stateMachine.setScore(stateMachine.getScore() - 200);
    }
}
