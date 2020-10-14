package own.jdk.multiThreadInAction.chapter5;

import own.jdk.multiThreadInAction.util.Debug;
import own.jdk.multiThreadInAction.util.Tools;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.atomic.AtomicBoolean;

//@WebListener
public class ThreadManagementContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 创建并启动一个数据库监控线程
        AbstractMonitorThread databaseMonitorThread;
        databaseMonitorThread = new AbstractMonitorThread(2000) {
            @Override
            protected void doMonitor() {
                Debug.info("Monitoring database...");
                // ...

                // 模拟实际的时间消耗
                Tools.randomPause(100);
            }
        };
        databaseMonitorThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 停止所有登记的线程
        ThreadTerminationRegistry.INSTANCE.clearThreads();
    }

    /**
     * 抽象监控线程
     */
    static abstract class AbstractMonitorThread extends Thread {

        // 监控周期
        private final long interval;

        // 线程停止标记
        final AtomicBoolean terminationToken = new AtomicBoolean(false);

        public AbstractMonitorThread(long interval) {
            this.interval = interval;
            // 设置为守护线程!
            setDaemon(true);
            ThreadTerminationRegistry.Handler handler;
            handler = new ThreadTerminationRegistry.Handler() {
                @Override
                public void terminate() {
                    terminationToken.set(true);
                    AbstractMonitorThread.this.interrupt();
                }
            };
            ThreadTerminationRegistry.INSTANCE.register(handler);
        }

        @Override
        public void run() {
            try {
                while (!terminationToken.get()) {
                    doMonitor();
                    Thread.sleep(interval);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                // 什么也不做
            }
            Debug.info("terminated:%s", Thread.currentThread());
        }

        // 子类覆盖该方法来实现监控逻辑
        protected abstract void doMonitor();
    }
}
