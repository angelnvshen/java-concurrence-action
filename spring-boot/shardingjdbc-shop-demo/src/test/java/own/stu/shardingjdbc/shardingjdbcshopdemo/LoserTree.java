package own.stu.shardingjdbc.shardingjdbcshopdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * 败者树,对<b>多个有序</b>的数据源进行归并排序<br>
 * 原理：2个子结点比较后的败者（较大值）放入它们的父结点，而胜者（较小值）送到它们父结点的父节点去再作比较
 *
 * @param <T>
 */
public class LoserTree<T> {
    /**
     * 数据源列表,为叶子节点提供数据,数据源的输出顺序<b>必须有序</b>
     */
    private Iterator<T>[] datas;
    /**
     * 叶子节点,存的是实际的数据<br>
     * 叶子节点和数据源是一一对应的,即第一个叶子节点记录第一个数据源的当前数据
     */
    private Object[] leafs;
    /**
     * 非叶子节点,记录叶子节点的下标, 根据节点的值可以定位到叶子节点所指向的数据<br>
     * 根据这个数组得到有序的数据
     */
    private int[] nodes;

    /**
     * 叶子节点数据的比较对象
     */
    private Comparator<T> comparator;

    /**
     * 构造方法,按照元素的Comparable接口实现进行排序
     */
    public LoserTree(List<Iterator<T>> branches) {
        this(branches, new Comparator<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public int compare(T o1, T o2) {
                return ((Comparable<T>) o1).compareTo(o2);
            }
        });
    }

    /**
     * 构造方法, 指定数据源分支的迭代器和元素比较对象<br>
     * 迭代器的输出必须有序并且与Comparator对象的比较结果保持一致
     *
     * @param brs
     * @param comparator
     */
    @SuppressWarnings("unchecked")
    public LoserTree(List<Iterator<T>> brs, Comparator<T> comparator) {
        this.datas = brs.toArray(new Iterator[0]);
        this.comparator = comparator;
        this.init();
    }

    /**
     * 初始化构建败者树<br>
     */
    private void init() {
        int size = this.datas.length;
        this.leafs = new Object[size];
        this.nodes = new int[size];

        // 为叶子节点赋值
        for (int i = 0; i < size; i++) {
            this.put(i);
        }

        // 找到叶子节点中最小值的下标
        int winner = 0;
        for (int i = 1; i < size; i++) {
            if (loser(i, winner)) {
                winner = i;
            }
        }
        // 非叶子节点全部初始化为最小值对应的叶子节点下标
        Arrays.fill(nodes, winner);

        // 从后向前依次调整非叶子节点
        for (int i = size - 1; i >= 0; i--)
            adjust(i);
    }

    /**
     * 设置第index个叶子节点的下一个数据<br>
     * 如果数据源已结束,则设置为null
     *
     * @param index
     */
    private void put(int index) {
        Iterator<T> branch = this.datas[index];
        this.leafs[index] = branch.hasNext() ? branch.next() : null;
    }

    /**
     * 获取第index个叶子节点的当前数据
     *
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    private T get(int index) {
        return (T) leafs[index];
    }

    /**
     * 判断index1对应的节点是否<b>小于</b>index2对应的节点
     *
     * @param index1
     * @param index2
     * @return
     */
    private boolean loser(int index1, int index2) {
        T t1 = (T) get(index1);
        T t2 = (T) get(index2);
        if (t1 == null)
            return false;
        if (t2 == null)
            return true;
        // 这里, 当叶节点数据相等时比较分支索引是为了实现排序算法的稳定性
        int n = comparator.compare(t1, t2);
        return n != 0 ? n < 0 : index1 < index2;
    }

    /**
     * 调整第index个叶子节点<br>
     * 具体调整过程为: 叶子节点和父节点比较, 败者（较大值）留在父节点位置, 胜者（较小值）继续和祖父节点比较，直至最终
     *
     * @param index
     */
    private void adjust(int index) {
        int size = this.datas.length;
        int t = (size + index) / 2; // 父节点
        while (t > 0) {
            // 如果当前值比父节点要大，那么留在父节点位置；否则继续和祖父节点比较
            if (loser(nodes[t], index)) {
                int temp = nodes[t];
                nodes[t] = index;
                index = temp;
            }
            t /= 2;
        }
        nodes[0] = index; // 根节点始终为最小值
    }

    /**
     * 依次读取数据源的数据进行归并排序, 返回排序后的数据列表<br>
     *
     * @return 从小到大的顺序
     */
    public List<T> merge(int bufferSize) {
        List<T> list = new ArrayList<T>();
        T top = null;
        while ((top = get(nodes[0])) != null) {
            list.add(top); // 取得最小值
            put(nodes[0]); // 索引对应的数据被取出，需要从数据源中再读入一个
            adjust(nodes[0]); // 根据新插入的叶子节点重新调整树
            if (list.size() == bufferSize)
                break;
        }
        return list;
    }
}