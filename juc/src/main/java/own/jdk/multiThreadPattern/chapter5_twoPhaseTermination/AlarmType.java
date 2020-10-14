package own.jdk.multiThreadPattern.chapter5_twoPhaseTermination;

public enum AlarmType {
    FAULT("fault"), RESUME("resume");
    private final String name;

    AlarmType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
