package own.state.singletonCommonState;

import own.state.State;

public interface IMario {
    State getName();

    void obtainMushRoom(MarioStateMachine4 stateMachine);

    void obtainCape(MarioStateMachine4 stateMachine);

    void obtainFireFlower(MarioStateMachine4 stateMachine);

    void meetMonster(MarioStateMachine4 stateMachine);
}