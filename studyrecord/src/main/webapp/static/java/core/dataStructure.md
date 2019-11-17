#### **二叉树的后继和前驱**

针对给定的遍历顺序来讲，前，中，后，层。
如二叉树：
       1
  2        3
4  5    6   7

以结点2为例，
前序遍历下，遍历序列为1 2 4 5 3 6 7 故前驱和后继为1、4
中序遍历下，遍历序列为4 2 5 1 6 3 7 故前驱和后继为4、5
后续遍历下，遍历序列为4 5 2 6 7 3 1 故前驱和后继为5、6



#### PriorityQueue （jdk)  heap

由数组实现的最小堆

```java
boolean offer(E e) {
  grow()// size >= queue.capacity; 容量：<= 64 ? 2*capacity:1.5*capacity; copy oldArr to nArr
  siftUp(i, e); //将e赋值给当前size位置，然后上虑。目的是维持最小堆，如果使用swap，没有移动效率高。
}

// 
void siftUpComparable(int k, E x) {
  Comparable key = (Comparable)x;
  while(k > 0){
    int parent = k >>> 1;
    Objcet e = queue[parent];
    if(key.compare(e) >= 0){
			break;
    }
    queue[k] = e;
    k = parent
  }
  queue[k] = key;
}

 E poll() {
   E result = queue[0];
   E x = queue[--size]; queue[size]==null;
   siftDown(0, x);
   return result;
 }
	
	// 遍历非叶子节点，当前节点的值赋值为两个孩子节点中较小元素的值，然后将key赋值合适位置。
 void siftDownComparable(int k, E x) {
   Comparable<? super E> key = (Comparable<? super E>)x;
   int half = size >>> 1;循环非叶子节点
   while(k < half){
     int child = (k << 1) + 1;
     Object c = queue[child];
     int right = child + 1;
     if(right < size && ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)){
       c = queue[child=right];
     }
     if(key.compareTo(c) <= 0){
       break;
     }
     queue[k] = c;
     k = child;
   }
   queue[k] = key;
 }

private void heapify() {
  for (int i = (size >>> 1) - 1; i >= 0; i--)         
    siftDown(i, (E) queue[i]);
}
```

find Kth largest element in the array problem:
1:降序排序，输出k-1th的元素。T(N) = O(NlogN)
	使用简单排序，如冒泡，T(N) = O(NK)
2:建立k个元素的最小堆，输出堆顶元素即可。(为什么不使用最大堆，如 6 1 2 ,当再插入4时，需要找到1并将1替换成4，而查找1需要遍历整个堆)
	T(N) = O(NlogK) , S(N) = O(K)
3:将原数组构建成最大堆，删除头元素，放置到数组末尾，然后第k次删除的元素就是需要找到的值。
	T(N) = N (构建堆) + O(KlogN); S(N) = 如果使用递归O(logN),不适用递归就是O(1)