package own.state;

public class MarioStateMachine {
    private int score;
    private State currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = State.SMALL;
    }

    public void obtainMushRoom() {

        if (currentState == State.SMALL) {
            currentState = State.SUPER;
            score += 100;
        }
    }

    public void obtainCape() {

        if (currentState == State.SMALL || currentState == State.SUPER) {
            currentState = State.CAPE;
            score += 200;
        }
    }

    public void obtainFireFlower() {

        if (currentState == State.SMALL || currentState == State.SUPER) {
            currentState = State.FIRE;
            score += 300;
        }
    }

    public void meetMonster() {
        if (currentState == State.SUPER) {
            score -= 100;
        } else if (currentState == State.CAPE) {
            score -= 200;
        } else if (currentState == State.FIRE) {
            score -= 300;
        }
        currentState = State.SMALL;
    }

    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState;
    }
}