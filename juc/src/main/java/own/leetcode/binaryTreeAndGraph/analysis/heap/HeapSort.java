package own.leetcode.binaryTreeAndGraph.analysis.heap;

import java.util.Comparator;

public class HeapSort<T> {
    private Comparator<T> comparator;

    public HeapSort(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public HeapSort() {
    }

    private int compare(T a, T b) {
        if (comparator != null) {
            return comparator.compare(a, b);
        } else {
            Comparable c = (Comparable) a;
            return c.compareTo(b);
        }
    }

    private void siftDown(int k, T[] arrays, int size) {
        int half = size >>> 1;
        T e = arrays[k];
        while (k < half) {
            int leftChild = (k << 1) + 1;
            T value = arrays[leftChild];
            int rightChild = leftChild + 1;
            if (rightChild <= size && compare(value, arrays[rightChild]) < 0) {
                value = arrays[leftChild = rightChild];
            }
            if (compare(e, value) >= 0) {
                break;
            }

            arrays[k] = arrays[leftChild];
            k = leftChild;
        }
        arrays[k] = e;
    }

    /**
     * @param arrays
     * @param p         需要处理的树的根节点
     * @param lastIndex 需要处理的树的最后一个非叶子节点
     * @param size
     */
    private void heapifyPostOrder(T[] arrays, int p, int lastIndex, int size) {
        if (p <= lastIndex) {
            heapifyPostOrder(arrays, (p << 1) + 2, lastIndex, size);
            heapifyPostOrder(arrays, (p << 1) + 1, lastIndex, size);
            siftDown(p, arrays, size);
        }
    }

    private void swap(T[] arrays, int a, int b) {
        T tmp = arrays[a];
        arrays[a] = arrays[b];
        arrays[b] = tmp;
    }

    /**
     * 建立最大堆，然后堆顶元素和最后一个元素交换，下滤堆顶元素 （size - 1），循环 （length - 1, 1), 最后一个堆顶元素肯定是最小值
     *
     * @param arrays
     */
    public void heapSort(T[] arrays) {
        int length = arrays.length;
        heapifyPostOrder(arrays, 0, length >>> 1, length - 1);
        for (int i = length - 1; i > 0; i--) {
            swap(arrays, 0, i);
            siftDown(0, arrays, i - 1);
        }
    }

}
