package own.jdk.interrupted;

import java.util.concurrent.BlockingQueue;

/**
 * 响应中断
 * 有两种实用策略可用于处理 InterruptedException:
 * <p>
 * 1： 传递异常（可能在执行某个特定于任务的清除操作之后），从而使你的方法也成为可中断的阻塞方法。
 * 2： 恢复中断状态，从而使调用栈中的上层代码能够对其进行处理。
 */
public class InterruptedTest2 {

    /**
     * 对于一些不支持取消但仍可以调用可中断阻塞方法的操作，它们必须在循环中调用这些方法，并在发现中断后重新尝试。
     * 在这种情况下，它们应该在本地保存中断状态，并在返回前恢复状态而不是捕获 InterruptException 时恢复状态。
     * <p>
     * 如下所示：
     */
    public static class NonCancelableTask {

        //将 InterruptedException 传递给调用者
        BlockingQueue<Task> queue;

        public Task getNextTask() throws InterruptedException {
            return queue.take();
        }

        // 不可取消的任务在退出前恢复中断
        public Task getNextTask(BlockingQueue<Task> queue) {
            boolean interrupted = false;
            try {
                while (true) {
                    try {
                        return queue.take();
                    } catch (InterruptedException e) {
                        interrupted = true;
                        // 重新尝试
                    }
                }
            } finally {
                if (interrupted)
                    Thread.currentThread().interrupt();
            }
        }
    }

    interface Task {

    }
}
