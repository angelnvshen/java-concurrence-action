#### hashMap (1.7 vs 1.8)

1.7 采用 数组 + 链表，1.8 数组+链表|红黑树
1.7 的插入式头插法，由于在resize时是逆序操作每个node的元素，所以多线程会出现环形链表，从而引起死循环问题。1.8使用的是尾插法。
1.7扩容后新元素的位置是hash&(length-1),lenght为扩容后的capacity，这也解释了为什么扩容一定是**2的多少次幂的**原因，因为最后一个二进制数一定都是1组成(length -1)，从而尽量减少hash碰撞。
而1.8则是根据hash码最新一个需要参与运算的为是1还是0，就直接迅速计算出了扩容后的储存方式。如果是0，还是以前的位置，如果是1，则以前位置+oldcapacity的值即是新位置。

tips hashmap
为什么 HashMap 中 String、Integer 这样的包装类适合作为 key 键![](/Users/my/IdeaProjects_own/core/studyrecord/src/main/webapp/picture/70.png)

#### linkedhashmap

默认是保留插入的顺序，accessOrder 这个参数显式设置为 true，代表以访问顺序进行迭代。原理是每个node节点多了两个指针，分别指向前一个node和后一个node。

```java
static class Entry<K,V> extends HashMap.Node<K,V> {
    Entry<K,V> before, after;
    Entry(int hash, K key, V value, Node<K,V> next) {
        super(hash, key, value, next);
    }
}
// get(Object key) | getOrDefault(Object key, V defauleValue) 后如果是accessOrder =true,将
// 访问过得node节点移动到last
void afterNodeAccess(Node<K,V> e) { // move node to last
  if (accessOrder && (last = tail) != e) {...}
}
```