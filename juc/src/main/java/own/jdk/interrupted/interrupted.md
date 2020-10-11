### 响应中断的方法和不响应中断的方法：

**响应中断的方法**：线程进入等待或是超时等待的状态后，调用interrupt方法都是会响应中断的，
所以响应中断的方法：

1. Object.wait()
2. Thread.join
3. Thread.sleep
4. LockSupport.park的有参和无参方法。

**不响应中断的方法**：线程进入阻塞状态后，是不响应中断的，等待进入<u>synchronized的方法或是代码块</u>，都是会被阻塞的，此时不会响应中断，另外还有一个不响应中断的，那就是阻塞在<u>ReentrantLock.lock</u>方法里面的线程，也是不响应中断的，如果想要响应中断，可以使用ReentrantLock.lockInterruptibly方法

其ReentrantLock底层是使用LockSupport.park方法进行等待的，前面说了LockSupport.park是响应中断的，当线程进入ReentrantLock.lock方法里面进行阻塞后，此时调用Thread.interrupt()方法之后，该线程是会被中断被唤醒的，但是唤醒之后，会调用LockSupport.park再次进入等待状态，所以仅从宏观（表面）上面看ReentrantLock.lock是不支持响应中断的，从微观（原理）上面讲ReentrantLock.lock内部确实中断了响应，但是还是会被迫进行等待状态。

### 清除中断标志位的方法：

对于Object.wait()、Thread.join、Thread.sleep方法都是会清除中断标志位了

而对于LockSupport.park方法，是不会清除中断标志位的，不清除中断标志位意味着调用Thread.interrupt方法之后，碰到多个LockSupport.park方法线程不会进行等待状态了。
