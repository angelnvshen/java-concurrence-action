## 1:algorithms 4th:

###graph: 
#### direct
#### un-direct
#### minimum-spanning-tree
#### shortest-path-tree


### fundaments
#### 内存
      分析内存的使用会比分析程序的运行时间简单的多，主要原因是它涉及的程序语句较少（只有申明语句）
    且在分析中我们会将复杂的对象简化为原始数据类型，而原始数据类型的内存使用是预先定义好的，而且很好理解：
    只需将变量的数量和它们的类型所对用的字节分别相乘并汇总即可。
    
    原始数据类型的常见内存
    类型    字节
    boolean 1
    byte    1
    char    2
    int     4
    float   4
    long    8
    double  8
##### 1：对象
      一个对象所使用的内存量，需要将所有实例变量使用的内存与对象本身的开销（一般是16字节）相加。
    这些开销包括一个指向类的引用的、垃圾回收信息以及同步信息。
      一般内存的使用都会被填充为8的倍数。
    // 一个Integer对象使用24个字节（16字节的对象开销和4个字节保存int值，以及4个填充字节）
    public class Integer{
      private int x;
    }
    // 一个Date对象使用32个字节（16字节的对象开销和3*4字节保存int值，以及4个填充字节）
    public class Date{
      private int year;
      private int month;
      private int day;
    }
    // 一个Counter对象使用32个字节（16字节的对象开销和4字节保存int值,8字节用于String变量的引用，以及4个填充字节）；
    // 这个内存使用总量并不包含String值所使用的内存总量。
    public class Counter{
      private String name;
      private int count;
    }
    // 一个Node对象使用40个字节（16字节的对象开销和 2*8字节Item和Node属性的引用，以及8个字节的引用[一个指向外部类的引用]）；
    // Node对象（内部类）
    public class Node{
      private Item item;
      private Node next;
    }
##### 2：链表
    一个含有N个整数的基于链表的栈需要使用（32+64N)个字节。
    {edu.princeton.cs.algs4.Stack}: 16字节的对象开销，8字节的引用和4字节int值和4个填充字节；每个元素（[Node]对象40字节 + [Integer]对象 24字节）
    所以需要(32+64N)个字节。 
##### 3：数组
    java中的数组被实现为对象，他们一般需要记录长度需要额外的内存。
    一个原始数据类型的数组一般需要24字节的头信息（16字节的对象开销和4字节用于保存长度以及4字节填充）在加上保存值所需要的内存。
    一个含有N个int值的数组需要使用（24+4N)个字节（会被填充为8的倍数） 
##### 4:字符串对象
      String的标准实现包含4个实例变量：一个指向字符数组的引用（8字节）和3个int值。第一个int值描述的是字符数组中的偏移量，第二个int是一个计数器（字符串的长度）
    第三个int值是一个散列值，它在某些情况下可以节省一些计算。因此每个String对象总共会使用40字节（16字节的对象开销，8字节引用，3*4字节的int值，和4字节的填充）。
    这是除字符数组之外的字符串所需的内存空间。
      一个长度为N的String对象一般需要使用40字节（Stringd对象本身）加上（24+2N）字节（字符数组），总共（64+2N）字节。
    
<<<<<<< HEAD
### graph    
### string
#### lsd 地位优先排序
    
=======

>>>>>>> 0fd944859895ed1d9bde6577912ca34e3775f35f
## 2:jdk1.8 new (test : own.sut.jkd8):
### lambda
### stream
### optional
### interface : 
> 1：类优先（父类和实现的接口中有同样的方法，类优先）

> 2: 接口冲突，实现多个接口中，如果有default方法名称一样，类需要指定一个接口中的类进行实现