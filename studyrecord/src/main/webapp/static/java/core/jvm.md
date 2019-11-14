#### 1: 内存模型

栈，本地方法栈，程序计数器，堆，元数据区（方法区）

#### 2: 判断对象是否存活算法

引用计数，缺点（循环引用的问题）

引用可达分析。GCroots 包括：元数据区的类静态属性引用，常量数据引用，栈中引用的对象，本地方法栈中引用的对象。

####3: 垃圾回收算法

标记-清除，缺点会产生内存碎片

复制，缺点浪费内存

标记-整理算法

#### 4：垃圾收集器类型

young 区：serial，parNew，parallel scavenge 

old区：serial-old, CMS, parallel old

##### CMS 的流程

初始化标记，标记GC roots 能直接关联的对象。stop the world

并发标记，进行GC rootsTracing的过程，根据类似图的搜索找到与root对象关联的对象。 与用户线程同步

重新标记，修正并发标记期间，因用户线程运行而导致标记产生变动的那一部分对象的标记记录。stop the world

并发清理。 与用户线程同步

G1



####5:ThreadLocal

`ThreadLocal`提供线程内部的局部变量，在本线程内随时随地可取，隔离其他线程。

thread -> threadlocalMap-> entry[] -> entry extends weakrefrence -> key(threadlocal) : value

Threadlocalmap 是一个customized hashmap。处理哈希冲突的方法是线性探查法。

`ThreadLocal`内存泄漏的前因后果，那么怎么避免内存泄漏呢？

Threadlocal 被引用的元素每次使用完`ThreadLocal`，都调用它的`remove()`方法，清除数据。



#### 6: introspector

内省是指计算机程序在运行时（Run time）检查对象（Object）类型的一种能力，通常也可以称作运行时类型检查。内省机制是通过反射来实现的，BeanInfo用来暴露一个bean的属性、方法和事件，以后我们就可以操纵该JavaBean的属性。