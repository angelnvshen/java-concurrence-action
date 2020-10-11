package own.jdk.multiThreadInAction.chapter4.demo2;

import org.apache.ibatis.io.Resources;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * java -Xms96m -Xmx128m -XX:NewSize=64m -XX:SurvivorRatio=32
 * -Dx.stat.task=own.jdk.multiThreadInAction.chapter4.demo2.MultithreadedStatTask -Dx.input.buffer=8192
 * -Dx.block.size=2000 own.jdk.multiThreadInAction.chapter4.demo2.CaseRunner4_2
 */
public class CaseRunner4_2 {

    public static void main(String[] args) throws Exception {
//        AppWrapper.invokeMain0(CaseRunner4_2.class, args, false);
        main0(args);
    }

    public static void main0(String[] args) throws Exception {
        int argc = args.length;
        // 根据指定的日志文件创建唯一一个输入流
        InputStream in = createInputStream();
        // 一对请求与响应之间的消息唯一标识的后3位值之差
        int traceIdDiff;
        // 待统计的操作名称
        String expectedOperationName;
        // 可选参数：采样周期（单位：秒）
        int sampleInterval;
        /*
         * 可选参数：指定一个以逗号分割的列表，仅发送给该列表中的设备的请求才会被统计在内。 默认值"*"表示不对外部设备做要求。
         */
        String expectedExternalDeviceList;

        traceIdDiff = argc >= 1 ? Integer.valueOf(args[0]) : 3;
        expectedOperationName = argc >= 2 ? args[1] : "sendSms";
        sampleInterval = argc >= 3 ? Integer.valueOf(args[2]) : 10;
        expectedExternalDeviceList = argc >= 4 ? args[3] : "*";

        // 创建执行统计的任务实例
        Runnable task = createTask(in, sampleInterval, traceIdDiff,
                expectedOperationName, expectedExternalDeviceList);

        // 直接在main线程中执行统计任务
        task.run();
    }

    private static Runnable createTask(InputStream in, int sampleInterval,
                                       int traceIdDiff, String expectedOperationName,
                                       String expectedExternalDeviceList) throws Exception {
        String taskClazz = System.getProperty("x.stat.task");

        taskClazz = null == taskClazz ? "own.jdk.multiThreadInAction.chapter4.demo2.MultithreadedStatTask" //SimpleStatTask
                : taskClazz;

        Class<?> clazz = Class.forName(taskClazz);
        Constructor<?> constructor = clazz.getConstructor(new Class[]{
                InputStream.class, int.class, int.class, String.class, String.class});

        Runnable st = (Runnable) constructor.newInstance(new Object[]{in,
                sampleInterval, traceIdDiff, expectedOperationName,
                expectedExternalDeviceList});
        return st;
    }

    private static InputStream createInputStream() throws IOException {
        final AtomicBoolean readerClosed = new AtomicBoolean(false);
        InputStream dataIn = Resources
                .getResourceAsStream("multiThreadInAction/in.dat");
        final BufferedReader bfr = new BufferedReader(new InputStreamReader(
                dataIn)) {
            @Override
            public void close() throws IOException {
                super.close();
                readerClosed.set(true);
            }
        };
        SequenceInputStream si = new SequenceInputStream(
                new Enumeration<InputStream>() {
                    String fileName = null;

                    @Override
                    public boolean hasMoreElements() {
                        if (readerClosed.get()) {
                            return false;
                        }
                        try {
                            fileName = bfr.readLine();
                            if (null == fileName) {
                                bfr.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            fileName = null;
                        }
                        return null != fileName;
                    }

                    @Override
                    public InputStream nextElement() {
                        InputStream in = null;
                        if (null != fileName) {
                            try {
                                in = Resources.getResourceAsStream("multiThreadInAction/inputFiles/"
                                        + fileName);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        return in;
                    }

                });

        return si;

    }

}