package own.jdk.multiThreadInAction.chapter4.demo2;

import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.SimpleTimeZone;

public abstract class AbstractStatTask implements Runnable {

    private static final String TIME_STMAP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    private final Calendar calendar;

    private final SimpleDateFormat sdf;

    // 采样周期
    private final int sampleInterval;

    protected final StatProcessor recordProcessor;

    public AbstractStatTask(int sampleInterval, int traceIdDiff,
                            String expectedOperationName, String expectedExternalDeviceList) {
        this(sampleInterval, new RecordProcessor(sampleInterval,
                traceIdDiff,
                expectedOperationName, expectedExternalDeviceList));
    }

    public AbstractStatTask(int sampleInterval, StatProcessor recordProcessor) {
        SimpleTimeZone stz = new SimpleTimeZone(0, "UTC");
        this.sdf = new SimpleDateFormat(TIME_STMAP_FORMAT);
        sdf.setTimeZone(stz);

        this.calendar = Calendar.getInstance(stz);

        this.sampleInterval = sampleInterval;
        this.recordProcessor = recordProcessor;
    }

    @Override
    public void run() {
        // 执行统计逻辑
        try {
            doCalculate();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // 获取统计结果
        Map<Long, DelayItem> result = recordProcessor.getResult();

        //输出统计结果
        report(result);
    }

    private void report(Map<Long, DelayItem> summaryResult) {
        final PrintStream ps = System.out;

        int sampleCount;
        ps.printf("%s\t\t%s\t%s\t%s\n", "Timestamp", "AvgDelay(ms)", "TPS", "SampleCount");
        for (DelayItem delayItemData : summaryResult.values()) {
            sampleCount = delayItemData.getSampleCount().get();

            ps.printf("%s%8d%8d%8d%n",
                    getUTCTimeStamp(delayItemData.getTimeStamp()),
                    delayItemData.getTotalDelay().get() / sampleCount,
                    sampleCount / sampleInterval,
                    sampleCount);
        }
    }

    private String getUTCTimeStamp(long timeStamp) {
        calendar.setTimeInMillis(timeStamp);
        String formatTime = sdf.format(calendar.getTime());
        return formatTime;
    }

    protected abstract void doCalculate() throws IOException, InterruptedException;
}
