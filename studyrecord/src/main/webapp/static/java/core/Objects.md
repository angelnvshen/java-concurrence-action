#### 对象怎么计算大小

1：对象头[地址 4字节，标记(锁相关，hash，age 计数) 8字节，是否是数组-是长度 4字节]
2：内容 （8个基本数据元素大小，对象引用 4）
3：填充（对象大小必须是8的倍数）

#### classloader

类加载器在尝试自己去查找某个类的字节代码并定义它时，会先代理给其父类加载器，由父类加载器先去尝试加载这个类，依次类推。原因为了保证 Java 核心库的类型安全。

不同的类加载器为相同名称的类创建了额外的名称空间。相同名称的类可以并存在 Java 虚拟机中，只需要用不同的类加载器来加载它们即可。不同类加载器加载的类之间是不兼容的，这就相当于在 Java 虚拟机内部创建了一个个相互隔离的 Java 类空间。

注意Class<?> loadClass(String name)  中name是 package + className。

打破双亲委派机制则不仅要继承ClassLoader类，还要重写loadClass和findClass方法。

调用逻辑：loadClass -> findLoadedClass->findClass->defineClass