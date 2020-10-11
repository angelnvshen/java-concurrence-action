package own.jdk.multiThreadInAction.chapter5.demo1;

import own.jdk.multiThreadInAction.util.Tools;

public class CaseRunner5_1 {
    final static AlarmAgent alarmAgent;

    static {
        alarmAgent = AlarmAgent.getInstance();
        alarmAgent.init();
    }

    public static void main(String[] args) throws InterruptedException {

        alarmAgent.sendAlarm("Database offline!");
        Tools.randomPause(12000);
        alarmAgent.sendAlarm("XXX service unreachable!");
    }
}