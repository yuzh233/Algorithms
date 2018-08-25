package chapter_2._4priority_queues;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/24 13:45
 * <p>
 * 基于有序数组实现的优先队列
 */
public class MaxPQ4Array<Key extends Comparable<Key>> implements IMaxPQ<Key> {
    private Key[] a;
    private int n;

    /**
     * 创建一个优先队列
     */
    MaxPQ4Array() {
        this(5);
    }

    /**
     * 创建一个初始容量的优先队列
     */
    MaxPQ4Array(int max) {
        a = (Key[]) new Comparable[max];
    }

    /**
     * 用arr[]中的元素创建一个优先队列
     */
    MaxPQ4Array(Key[] arr) {
        a = (Key[]) new Comparable[arr.length];
        for (int i = 0; i < arr.length; i++) {
            insert(arr[i]);
        }
    }

    @Override
    public void insert(Key v) {
        if (n == a.length) resize(n * 2);
        // 较大元素右移（插入排序）
        int i;
        for (i = n; i > 0 && less(v, a[i - 1]); i--) {
            a[i] = a[i - 1];
        }
        a[i] = v;
        n++;
    }

    @Override
    public Key max() {
        if (isEmpty()) return null;
        return a[n - 1];
    }

    @Override
    public Key delMax() {
        if (isEmpty()) return null;
        Key max = a[n - 1];
        a[n - 1] = null;
        n--;
        if (n > 0 && a.length / 4 == n) resize(a.length / 2);
        return max;
    }

    public void show() {
        for (int i = 0; i < n; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    private void resize(int max) {
        Key[] temp = (Key[]) new Comparable[max];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public boolean less(Key a, Key b) {
        return a.compareTo(b) < 0;
    }

    public void exch(Key[] a, int i, int j) {
        Key tem = a[i];
        a[i] = a[j];
        a[j] = tem;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    public static void main(String[] args) {
        MaxPQ4Array<Integer> queue = new MaxPQ4Array<>(5);
        queue.insert(4);
        queue.insert(2);
        queue.insert(5);
        queue.insert(1);
        queue.insert(3);
        queue.insert(3);
        queue.insert(7);
        queue.insert(43);
        queue.insert(100);

        queue.show();
        queue.delMax();
        queue.show();
    }
}
