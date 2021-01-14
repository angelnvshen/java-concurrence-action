### 线程同步机制的底层助手：内存屏障

内存屏障是对一类仅针对内存读、写操作指令的跨处理器架构的比较底层的抽象。

内存屏障是被插入到两个指令之间进行使用的，其作用是禁止编译器、处理器重排序从而保证有序性的。

按照内存屏障所起的作用，可以分为以下几种：

+ 按照**可见性保障**来划分，内存屏障可以分为 **加载屏障**（load barrier）和**存储屏障**（store barrier）,加载屏障的作用是刷新处理器缓存，存储屏障的作用是冲刷处理器缓存。
  
  JVM在MonitorExit（释放锁）对应的机器码指令之后插入一个存储屏障，这就保障了写线程在释放锁之前在临界区中对共享变量所做的更新对读线程的执行处理器来说是可同步的；相应的，JVM在MonitorEnter（申请所）对应的机器码指令之后临界区开始之前的地方插入一个加载屏障，这使得读线程的执行处理器能够将写线程对相应共享变量所做的更新从其他处理器同步到该处理器的高速缓存中。
  
  因此，可见性的保障是通过读线程和写线程成对的使用加载屏障和存储屏障实现的。

+ 按照**有序性保障**来划分，内存屏障可以分为**获取屏障**（Acquire barrier）和**释放屏障**（Release barrier）。
  
  获取屏障的使用方式是在一个读操作（包括read-modify-write以及普通的读操作）之后插入该内存屏障，起作用是禁止该读操作和其后的任何读写操作之间进行重排序，这相当于在进行后续的操作之前先要获取相应共享数据的所有权（这也是该屏障名称的由来）。
  
  释放屏障的使用方式是在一个写操作之前插入该内存屏障，其作用是禁止该写操作与其前边的任何读写操作之间进行重排序，这相当于在对相应共享数据操作结束后释放所有权（这也是该屏障名称的由来）。
  
  JVM在MonitorEnter(它包含了读操作)对应的机器码之后临界区开始之间的地方插入一个获取屏障，并在临界区结束之后MonitorExit(它包含了写操作)对应的机器码之前的地方插入一个释放屏障。
  
  可见，锁对有序性的保障是通过写线程和读线程配对使用获取屏障和释放屏障来实现的。

### 轻量级的同步工具：volatitle关键字

作用：包括保障可见性和有序性，保障long/double型变量读操作的原子性。

1. 对于volatile变量的写操作，JVM会在该操作之前插入一个释放屏障，并在该操作之后插入一个存储屏障。
   
   其中，释放屏障禁止了volatile写操作与该操作之前的任何读写操作进行重排序，从而保证了volatile写操作之前的任何读写操作会先于volatile写操作被提交，即其他线程看到写线程对volatile变量更新时，写线程在更新volatile变量之前所执行的内存操作的结果对于读线程来说必然是可见的。这就保障了读线程对写线程在更新volatile变量前对共享变量所执行的更新操作的感知顺序和相应的源代码顺序是一致的，即保障了有序性。

2. 对于volatile变量的读操作，JVM会在该操作之前插入一个加载屏障，并在该操作之后插入一个获取屏障

volatile 关键字的作用体现在对其所修饰的变量的读写操作上。

CAS只保证了共享变量更新操作的原子性，不保证可见性，所以依然采用volatile修饰共享变量。

### 死锁

**产生死锁的必要条件**:

+ 资源互斥

+ 资源不可抢夺

+ 占用并等待资源

+ 循环等待资源

这些条件是死锁产生的必要条件而非充分条件，也就是说只要产生了死锁，那么上面的条件一定同时成立，但是上述条件即使同时成立也不一定产生死锁。

**规避死锁的常见方法**：

+ 粗锁法 —使用一个粗粒度的锁代替多个锁

+ 锁排序法—相关线程使用全局统一的顺序申请锁

+ 使用ReentrantLock.tryLock(long, TimeUnit) 来申请锁

+ 使用开放调用（open call) —在调用外部方法时不加锁

+ 使用锁的替代品（无状态对象、线程特有对象以及volatile关键字等）