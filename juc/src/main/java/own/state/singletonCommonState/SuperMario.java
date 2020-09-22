package own.state.singletonCommonState;

import own.state.State;

public class SuperMario implements IMario {

    private static final SuperMario instance = new SuperMario();

    private SuperMario() {
    }

    public static SuperMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine4 stateMachine) {
        // do nothing...
    }

    @Override
    public void obtainCape(MarioStateMachine4 stateMachine) {
        stateMachine.setCurrentState(CapeMario.getInstance());
        stateMachine.setScore(stateMachine.getScore() + 200);
    }

    @Override
    public void obtainFireFlower(MarioStateMachine4 stateMachine) {
        stateMachine.setCurrentState(FireMario.getInstance());
        stateMachine.setScore(stateMachine.getScore() + 300);
    }

    @Override
    public void meetMonster(MarioStateMachine4 stateMachine) {
        stateMachine.setCurrentState(SmallMario.getInstance());
        stateMachine.setScore(stateMachine.getScore() - 100);
    }
}
