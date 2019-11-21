1：**RandomAccess** 标记接口只要作用是：`RandomAccess` 是 `List` 实现所使用的**标记接口**，用来表明其**支持快速（通常是固定时间）随机访问**。此接口的主要目的是允许一般的算法更改其行为，从而在将其应用到随机或连续访问列表时能提供良好的性能。

```java
				if (list instanceof RandomAccess){
            System.out.println("实现了RandomAccess接口，不使用迭代器");

            for (int i = 0;i < list.size();i++){
                System.out.println(list.get(i));
            }
        }else{
            System.out.println("没实现RandomAccess接口，使用迭代器");

            Iterator it = list.iterator();
            while(it.hasNext()){
                System.out.println(it.next());
            }
        }
```

