package chapter_2._4priority_queues;

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/25 13:27
 * <p>
 * 基于堆的完全二叉树
 */
public class MaxPQ<Key extends Comparable<Key>> implements IMaxPQ<Key> {
    private Key[] pq; // 基于堆的完全二叉树
    private int N; // 堆元素数量，数组索引和当前第几个元素顺序是一样的。比如第2个元素，数组索引是2。

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(int max) {
        pq = (Key[]) new Comparable[max + 1];
        N = 0;
    }

    public MaxPQ(Key[] arr) {
        pq = (Key[]) new Comparable[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            insert(arr[i]);
        }
    }

    @Override
    public void insert(Key v) {
        if (N == pq.length - 1) resize(pq.length * 2);
        pq[++N] = v;
        swim(N);
    }

    @Override
    public Key delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null; // 防止对象游离
        sink(1);
        if (N > 0 && (pq.length - 1) / 4 == N) resize(pq.length / 2);
        return max;
    }

    @Override
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    /**
     * 堆的上浮
     *
     * @param k 节点索引
     */
    public void swim(int k) {
        while (k > 1 && less(k / 2, k)) { // k > 1保证是根节点下的节点，k/2是k的父节点。
            exch(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 堆的下沉
     *
     * @param k 节点索引
     */
    public void sink(int k) {
        while (2 * k <= N) { // 如果元素从pq[0]开始，最后一个元素是pq[n-1]；但是这里元素从pq[1]开始，最后一个元素是pq[n]；
            int j = 2 * k; // 子节点1
            if (j < N && less(j, j + 1)) j++; // j < N 才能每次访问到最后一个子节点
            if (!less(k, j)) break;
            exch(k, j); // 子节点1 < 子节点2，父节点与子节点2交换；子节点1 > 子节点2，父节点与子节点1交换；
            k = j; // 子节点变为父节点
        }
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    private void resize(int max) {
        assert max > N;
        Key[] temp = (Key[]) new Comparable[max];
        for (int i = 1; i < N + 1; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public void exch(int i, int j) {
        Key tem = pq[i];
        pq[i] = pq[j];
        pq[j] = tem;
    }

    public void show() {
        for (int i = 0; i < N + 1; i++) {
            StdOut.print(pq[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        MaxPQ<Integer> queue = new MaxPQ<>(5);
        queue.insert(4);
        queue.insert(2);
        queue.insert(5);
        queue.insert(3);
        queue.insert(3);
        queue.insert(7);
        queue.insert(3);
        queue.insert(43);
        queue.insert(100);

        StdOut.println("size:" + queue.size());
        queue.delMax();
        queue.delMax();
        queue.delMax();
        queue.delMax();
        queue.delMax();
        queue.delMax();
        queue.delMax();
        queue.delMax();
        queue.delMax();
        queue.show();
    }
}
