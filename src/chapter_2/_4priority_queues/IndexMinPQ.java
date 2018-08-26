package chapter_2._4priority_queues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/25 20:15
 * <p>
 * 索引优先队列的实现
 */
public class IndexMinPQ<Element extends Comparable<Element>> {
    /**
     * 队列的元素个数
     */
    private int N;

    /**
     * 完全二叉树（二叉堆）：优先队列的表现形式，但是存储的不是元素，而是元素对应的“索引”。这里将索引称为 key，索引值称为 element。
     */
    private int[] key;

    /**
     * 存储 key 在优先队列的位置（key的索引），举例以方便理解：
     * <p>
     * - 执行insert(int k, Element element)时，我们将k放到队列的末尾再执行上浮下沉操作，又将k的元素值放在对应的地方elements[k]=element。
     * - insert操作没问题，体现不出keyIndex的作用。但是当我们执行修改操作时呢？
     * <p>
     * 假如 key[] = {9, 4, 1, 6, 7......};（解释一下：索引为9的元素值是根节点，4和1对应的元素分别是其两个子节点）
     * - 执行delete(int k)时，比如k=4。我们需要将键为4的元素删除，即`elements[4]=null`，问题来了，怎么在key[]中找到这个`元素4`的位置呢？
     * - - 方式一：通过遍历整个 key[] 找到 4。（如果这样就不用 keyIndex[]了）
     * - - 方式二：存放k的索引，存放 keyIndex[4]=2，我们可以通过`keyIndex[4]`知道key=4的元素在key[]中的下标是2了。
     */
    private int[] keyIndex;

    /**
     * 索引对应的元素值，因为上浮下沉操作的是该元素的索引，所以该值的索引不需要改变（索引已经在队列中存在）。删除时置为null即可。
     */
    private Element[] elements;

    IndexMinPQ() {
        this(1);
    }

    IndexMinPQ(int maxN) {
        elements = (Element[]) new Comparable[maxN + 1];
        key = new int[maxN + 1]; // key是用户存放的，并且最好要按照以0开始的顺序作为键，key的最大值决定keyIndex的长度。
        keyIndex = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) {
            keyIndex[i] = -1; // 初始化时二叉树不存在元素，硬是想看key[i]会返回-1，如果不设置会返回null。
        }
    }

    /**
     * 插入一个元素，将其和索引k关联
     */
    public void insert(int k, Element element) { // 若 k=2, element = A
        if (N == key.length - 1) resize(key.length * 2);
        N++;
        key[N] = k; // 放入队列数组末尾
        keyIndex[k] = N; // 以 2 作为索引，值为队列数组的索引放入keyIndex[]
        elements[k] = element; // 将以2为索引存储元素值
        swim(N); // 上浮操作的是元素的key
    }

    public Element min() {
        return elements[key[1]];
    }

    public int delMin() {
        int indexOfMin = key[1];
        exch(1, N--);
        sink(1);
        elements[key[N + 1]] = null;
        keyIndex[key[N + 1]] = -1;
        if (N > 0 && (key.length - 1) / 4 == N) resize(key.length / 2);
        return indexOfMin;
    }

    public boolean contains(int k) {
        return keyIndex[k] != -1;
    }

    /**
     * 上浮：传入的是二叉树的索引
     */
    public void swim(int k) {
        while (k > 1 && larger(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    /**
     * 下沉：传入的是二叉树的索引
     */
    public void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && larger(j, j + 1)) j++;
            if (!larger(k, j)) break; //break 说明 elements[key[k]] < elements[key[j]] 交换两者
            exch(k, j);
            k = j;
        }
    }

    /**
     * 比较：索引为i的节点值 > 索引为j的节点值 ? true : false
     * <p>
     * 传入的是二叉树的索引
     * 通过二叉树索引找到二叉树的值，通过二叉树的值找到元素值。
     */
    public boolean larger(int i, int j) {
        return elements[key[i]].compareTo(elements[key[j]]) > 0;
    }

    /**
     * 交换：传入的是二叉树的索引
     */
    public void exch(int i, int j) {
        int tem = key[i];
        key[i] = key[j];
        key[j] = tem;
        // 交换key之后还需要交换key所在的位置
        keyIndex[key[i]] = i; // 通过二叉树索引找到二叉树的值，在keyIndex中通过二叉树的值找到该值的索引。
        keyIndex[key[j]] = j;
    }

    /**
     * 返回最小元素索引
     */
    public int minIndex() {
        return key[1];
    }

    /**
     * 交换指定key的元素值
     */
    public void change(int k, Element element) {
        elements[k] = element;
        swim(k);
        sink(k);
    }

    /**
     * 删除指定key的元素
     */
    public void delete(int k) {
        int index = k;
        exch(index, N--); // 最后一个值与当前值交换，大小-1。
        swim(k);
        sink(k);
        elements[k] = null;
        keyIndex[k] = -1; // 存放当前索引的数组值置为-1
        if (N > 0 && (key.length - 1) / 4 == N) resize(key.length / 2);
    }

    private void resize(int max) {
        assert max > N;
        // 二叉树扩容
        int[] tempKey = new int[max];
        for (int i = 1; i < N + 1; i++) {
            tempKey[i] = key[i];
        }
        key = tempKey;
        // 二叉树索引扩容
        int[] tempKeyIndex = new int[max];
        for (int i = 1; i < N + 1; i++) {
            tempKeyIndex[i] = keyIndex[i];
        }
        keyIndex = tempKeyIndex;
        // 二叉树元素扩容
        Element[] tempElement = (Element[]) new Comparable[max];
        for (int i = 1; i < N + 1; i++) {
            tempElement[i] = elements[i];
        }
        elements = tempElement;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void show() {
        for (int i = 1; i < N + 1; i++) {
            if (keyIndex[i] != -1 && elements[i] != null) {
//                StdOut.println("key = " + key[i] + ", value = " + elements[key[i]]);
                StdOut.println(elements[key[i]]);
            }
        }
    }

    public static void main(String[] args) {
        IndexMinPQ<Integer> queue = new IndexMinPQ<>(5);
        for (int i = 1; i < 6; i++) {
            queue.insert(i, StdRandom.uniform(100));
        }

        queue.insert(6, 1998);
        queue.show();
        queue.delMin();
        queue.delMin();
        queue.delMin();
        queue.delMin();
        queue.delMin();
        queue.show();
        queue.size();
        StdOut.println("min: " + queue.min() + ", minIndex: " + queue.minIndex());
        StdOut.println("contains? " + queue.contains(3));
    }
}
