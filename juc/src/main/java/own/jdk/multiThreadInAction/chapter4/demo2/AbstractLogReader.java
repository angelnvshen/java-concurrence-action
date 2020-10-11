package own.jdk.multiThreadInAction.chapter4.demo2;

import own.jdk.multiThreadInAction.util.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 日志文件读取线程。
 */
public abstract class AbstractLogReader extends Thread {
    protected final BufferedReader logFileReader;
    // 表示日志文件是否读取结束
    protected volatile boolean isEOF = false;
    protected final int batchSize;

    public AbstractLogReader(InputStream in, int inputBufferSize, int batchSize) {
        this.logFileReader = new BufferedReader(new InputStreamReader(in), inputBufferSize);
        this.batchSize = batchSize;
    }

    protected RecordSet getNextToFill() {
        return new RecordSet(batchSize);
    }

    // 获取下一个记录集
    protected abstract RecordSet nextBatch()
            throws InterruptedException;

    // 发布指定的记录集
    protected abstract void publish(RecordSet recordBatch)
            throws InterruptedException;


    @Override
    public void run() {
        RecordSet recordSet;
        boolean eof = false;

        try {
            while (true) {
                recordSet = getNextToFill();
                recordSet.reset();

                eof = doFill(recordSet);
                publish(recordSet);
                if (eof) {
                    if (!recordSet.isEmpty()) {
                        publish(new RecordSet(1));
                    }
                    isEOF = eof;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Tools.silentClose(logFileReader);
        }
    }

    protected boolean doFill(final RecordSet recordSet) throws IOException {
        final int capacity = recordSet.capacity;

        String record;

        for (int i = 0; i < capacity; i++) {
            record = logFileReader.readLine();
            if (record == null) {
                return true;
            }
            // 将读取到的日志记录存入指定的记录集
            recordSet.putRecord(record);
        }

        return false;
    }
}