# Streams 的幕后原理

#### 流管道

一个*流管道* 包含一个*流来源*、0 或多个*中间操作*，以及一个*终止操作*。

流来源可以是集合、数组、生成器函数或其他任何适当地提供了其元素的访问权的数据源。中间操作将流转换为其他流 — 通过过滤元素 (`filter()`)，转换元素 (`map()`)，排序元素 (`sorted()`)，将流截断为一定大小 (`limit()`)，等等。终止操作包括聚合（`reduce()`、`collect()`），搜索 (`findFirst()`) 和迭代 (`forEach()`)。

流管道是惰性构造的。构造流来源不会计算流的元素，而是会确定在必要时如何找到元素。类似地，调用中间操作不会在元素上执行任何计算；只会将另一个操作添加到流描述的末尾。仅在调用终止操作时，管道才会实际执行相应的工作：计算元素，应用中间操作，以及应用终止操作。

####常用的流操作

在深入原理之前，我们有必要知道关于Stream的一些基础知识，关于Stream的操作分类，如表1-1所示。

表1-1 Stream的常用操作分类

<img src="../../../picture/WechatIMG264.tiff" width = "100%" height = "100%" alt="图片名称" align=center />

**Stream中的操作可以分为两大类**：中间操作与结束操作，中间操作只是对操作进行了记录，只有结束操作才会触发实际的计算（即惰性求值），这也是Stream在迭代大集合时高效的原因之一。中间操作又可以分为无状态（Stateless）操作与有状态（Stateful）操作，前者是指元素的处理不受之前元素的影响；后者是指该操作只有拿到所有元素之后才能继续下去。结束操作又可以分为短路与非短路操作，这个应该很好理解，前者是指遇到某些符合条件的元素就可以得到最终结果；而后者是指必须处理所有元素才能得到最终结果。

https://www.cnblogs.com/Dorae/p/7779246.html

https://cloud.tencent.com/developer/article/1347534

https://www.cnblogs.com/noteless/p/9505098.html

https://www.ibm.com/developerworks/cn/java/j-java-streams-3-brian-goetz/index.html

https://juejin.im/post/5bb227a1e51d450e9a2e4f42

continue