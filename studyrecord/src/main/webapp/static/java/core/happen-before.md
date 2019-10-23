## 【并发重要原则】happens-before理解和应用

## 1.happens-before的理解

### 1.1 为什么要有一个happens-before的原则？

结论：happens-before觉得着什么时候变量操作对你可见。

我们知道cpu的运行极快，而读取主存对于cpu而言有点慢了，在读取主存的过程中cpu一直闲着（也没数据可以运行），这对资源来说造成极大的浪费。所以慢慢的cpu演变成了多级cache结构，cpu在读cache的速度比读内存快了n倍。

当线程在执行时，会保存临界资源的副本到私有work memory中，这个memory在cache中，修改这个临界资源会更新work memory但并不一定立刻刷到主存中，那么什么时候应该刷到主存中呢？什么时候和其他副本同步？
而且编译器为了提高指令执行效率，是可以对指令重排序的，重排序后指令的执行顺序不一样，有可能线程2读取某个变量时，线程1还未进行写入操作。这就是线程可见性的来源。

针对以上两个问题，JMM给出happens-before通用的规则（注意这仅对java而言，其他的就布吉岛了）

### 1.2 happens-before原则有啥好处？

```cpp
i = 1;       //线程A执行
j = i ;      //线程B执行
```

j 是否等于1呢？假定线程A的操作（i = 1）happens-before线程B的操作（j = i）。
那么可以确定线程B执行后j = 1 一定成立。
如果他们不存在happens-before原则，那么j = 1 不一定成立。

（即使代码是先执行j=1,然后执行j=i，也不一定j=1,主要看是否符合happens-before）

### 1.3 happens-before原则

1. 如果操作1 happens-before 操作2，那么第操作1的执行结果将对操作2可见，而且操作1的执行顺序排在第操作2之前。
2. 两个操作之间存在happens-before关系，并不意味着一定要按照happens-before原则制定的顺序来执行。如果重排序之后的执行结果与按照happens-before关系来执行的结果一致，那么这种重排序并不非法。

### 1.4如何判断是否为 happens-before？

- 程序次序规则： 在一个单独的线程中，按照程序代码的执行流顺序，（时间上）先执行的操作happen—before（时间上）后执行的操作
  （**同一个线程中前面的所有写操作对后面的操作可见**）
- 管理锁定规则：一个unlock操作happen—before后面（时间上的先后顺序）对同一个锁的lock操作。
  （**如果线程1解锁了monitor a，接着线程2锁定了a，那么，线程1解锁a之前的写操作都对线程2可见（线程1和线程2可以是同一个线程）**）
- volatile变量规则：对一个volatile变量的写操作happen—before后面（时间上）对该变量的读操作。
  （**如果线程1写入了volatile变量v（临界资源），接着线程2读取了v，那么，线程1写入v及之前的写操作都对线程2可见（线程1和线程2可以是同一个线程）**）
- 线程启动规则：Thread.start()方法happen—before调用用start的线程前的每一个操作。
  （**假定线程A在执行过程中，通过执行ThreadB.start()来启动线程B，那么线程A对共享变量的修改在接下来线程B开始执行前对线程B可见。注意：线程B启动之后，线程A在对变量修改线程B未必可见。**）
- 线程终止规则：线程的所有操作都happen—before对此线程的终止检测，可以通过Thread.join（）方法结束、Thread.isAlive（）的返回值等手段检测到线程已经终止执行。
  (**线程t1写入的所有变量，在任意其它线程t2调用t1.join()，或者t1.isAlive() 成功返回后，都对t2可见。**)
- 线程中断规则：对线程interrupt()的调用 happen—before 发生于被中断线程的代码检测到中断时事件的发生。
  (**线程t1写入的所有变量，调用Thread.interrupt()，被打断的线程t2，可以看到t1的全部操作**)
- 对象终结规则：一个对象的初始化完成（构造函数执行结束）happen—before它的finalize（）方法的开始。
  (**对象调用finalize()方法时，对象初始化完成的任意操作，同步到全部主存同步到全部cache。**)
- 传递性：如果操作A happen—before操作B，操作B happen—before操作C，那么可以得出A happen—before操作C。
  （**A h-b B ， B h-b C 那么可以得到 A h-b C**）

### 1.5 一言以蔽之，这些规则背后的道理

在程序运行过程中，所有的变更会先在寄存器或本地cache中完成，然后才会被拷贝到主存以跨越内存栅栏（本地或工作内存到主存之间的拷贝动作），此种跨越序列或顺序称为happens-before。
**注：happens-before本质是顺序，重点是跨越内存栅栏**
通常情况下，写操作必须要happens-before读操作，即写线程需要在所有读线程跨越内存栅栏之前完成自己的跨越动作，其所做的变更才能对其他线程可见。



## 2.应用

### 2.1 单例模式

*单例模式可能存在问题哦，请看我的文章【单例模式】DCL的问题和解决方法*

**可以看出，如果有两个线程都执行过synchronized ，那么符合"管理锁定规则"，那么我们可以线程 singleton即使不加上volatile，也不会影响线程间的可见性**

```java
public class Singleton {     
    private static Singleton singleton;  
    private Singleton() {      }     
    public static Singleton getInstance() {     
        if (singleton == null) {    
            synchronized (Singleton.class) {     
                if (singleton == null) {     
                    Singleton temp = null;  
                    try {  
                        temp = new Singleton();    
                    } catch (Exception e) {   }  
                    if (temp != null) 
                        singleton = temp; 
                }    
            }    
        }    
        return singleton;    
    }  
}  
```

https://www.jianshu.com/p/7ee5dc71a7f8