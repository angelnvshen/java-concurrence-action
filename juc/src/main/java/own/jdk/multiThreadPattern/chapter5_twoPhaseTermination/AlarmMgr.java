package own.jdk.multiThreadPattern.chapter5_twoPhaseTermination;

import own.jdk.multiThreadInAction.util.Debug;
import own.jdk.multiThreadPattern.chapter4_guardedSuspension.AlarmInfo;

/**
 * 告警功能入口类
 */
public class AlarmMgr {

    private static final AlarmMgr INSTANCE = new AlarmMgr();

    // 告警发送线程
    private final AlarmSendingThread alarmSendingThread;

    // 私有构造器
    private AlarmMgr() {
        alarmSendingThread = new AlarmSendingThread();
    }

    // 返回类AlarmMgr的唯一实例
    public static AlarmMgr getInstance() {
        return INSTANCE;
    }

    private volatile boolean shutdownRequested = false;

    public int sendAlarm(AlarmType type, String id, String extraInfo) {
        Debug.info("Trigger alarm " + type + "," + id + ',' + extraInfo);

        int duplicateSubmissionCount = 0;

        try {
            AlarmInfo alarmInfo = new AlarmInfo(id, type);
            alarmInfo.setExtraInfo(extraInfo);

            duplicateSubmissionCount = alarmSendingThread.sendAlarm(alarmInfo);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return duplicateSubmissionCount;
    }

    public synchronized void shutdown() {
        if (shutdownRequested) {
            throw new IllegalStateException("shutdown already requested!");
        }

        alarmSendingThread.terminate();
        shutdownRequested = true;
    }
}
