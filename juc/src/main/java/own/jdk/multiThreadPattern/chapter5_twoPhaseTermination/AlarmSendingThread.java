package own.jdk.multiThreadPattern.chapter5_twoPhaseTermination;

import own.jdk.multiThreadPattern.chapter4_guardedSuspension.AlarmAgent;
import own.jdk.multiThreadPattern.chapter4_guardedSuspension.AlarmInfo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AlarmSendingThread extends AbstractTerminableThread {

    private final AlarmAgent alarmAgent = new AlarmAgent();

    /*
     * 告警队列。 模式角色：HalfSync/HalfAsync.Queue
     */
    private final BlockingQueue<AlarmInfo> alarmQueue;
    private final ConcurrentMap<String, AtomicInteger> submittedAlarmRegistry;

    public AlarmSendingThread() {
        alarmQueue = new ArrayBlockingQueue<>(100);
        submittedAlarmRegistry = new ConcurrentHashMap<>();

        alarmAgent.init();
    }

    @Override
    protected void doRun() throws Exception {
        AlarmInfo alarm;
        alarm = alarmQueue.take();
        terminationToken.reservations.decrementAndGet();

        try {
            // 将告警信息发送至告警服务器
            alarmAgent.sendAlarm(alarm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * 处理恢复告警：将相应的故障告警从注册表中删除， 使得相应故障恢复后若再次出现相同故障，该故障信息能够上报到服务器
         */
        if (AlarmType.RESUME == alarm.type) {
            String key =
                    AlarmType.FAULT.toString() + ':' + alarm.getId() + '@'
                            + alarm.getExtraInfo();
            submittedAlarmRegistry.remove(key);

            key =
                    AlarmType.RESUME.toString() + ':' + alarm.getId() + '@'
                            + alarm.getExtraInfo();
            submittedAlarmRegistry.remove(key);
        }
    }

    public int sendAlarm(final AlarmInfo alarmInfo) {
        AlarmType type = alarmInfo.type;
        String id = alarmInfo.getId();
        String extraInfo = alarmInfo.getExtraInfo();

        if (terminationToken.isToShutdown()) {
            // 记录告警信息
            System.err.println("rejected alarm:" + id + "," + extraInfo);
            return -1;
        }

        int duplicateSubmissionCount = 0;
        try {
            AtomicInteger prevSubmittedCounter;
            prevSubmittedCounter = submittedAlarmRegistry.putIfAbsent(type.toString()
                    + ':' + id + '@' + extraInfo, new AtomicInteger(0));

            if (prevSubmittedCounter == null) {
                alarmQueue.put(alarmInfo);
                terminationToken.reservations.incrementAndGet();
            } else {
                // 故障未恢复，不用重复发送告警信息给服务器，故仅增加计数
                duplicateSubmissionCount =
                        prevSubmittedCounter.incrementAndGet();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return duplicateSubmissionCount;
    }

    @Override
    protected void doCleanup(Exception cause) {
        if (cause != null && !(cause instanceof InterruptedException)) {
            cause.printStackTrace();
        }
        alarmAgent.disconnected();
    }
}
