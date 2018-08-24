package chapter_2._4priority_queues;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/24 13:45
 * <p>
 * 基于无序数组实现的优先队列
 */
public class MaxPQ4DisArray<Key extends Comparable<Key>> implements MaxPQ<Key> {
    private Key[] a;
    private int n;

    /**
     * 创建一个优先队列
     */
    MaxPQ4DisArray() {
        a = (Key[]) new Comparable[5];
    }

    /**
     * 创建一个初始容量的优先队列
     */
    MaxPQ4DisArray(int max) {
        a = (Key[]) new Comparable[max];
    }

    /**
     * 用arr[]中的元素创建一个优先队列
     */
    MaxPQ4DisArray(Key[] arr) {
        a = (Key[]) new Comparable[arr.length];
        for (int i = 0; i < arr.length; i++) {
            insert(arr[i]);
        }
    }

    @Override
    public void insert(Key v) {
        if (n == a.length) resize(n * 2);
        a[n++] = v;
    }

    @Override
    public Key max() {
        if (isEmpty()) return null;
        int maxIn = 0;
        Key max = a[maxIn];
        for (int i = 1; i < n && less(max, a[i]); i++) {
            exch(a, maxIn, i);
            maxIn = i;
        }
        return max;
    }

    @Override
    public Key delMax() {
        if (isEmpty()) return null;
        int j = 0;
        Key max = a[j];
        for (int i = 1; i < n; i++) {
            if (a[i].compareTo(max) > 0) {
                Key tem = max;
                max = a[i];
                j = i;
                a[i] = tem;
            }
        }
        // 最大元素与边界元素交换，因为是无需的数组，无需元素左移
        a[j] = a[n - 1];
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
        MaxPQ4DisArray<Integer> queue = new MaxPQ4DisArray<>(5);
        queue.insert(4);
        queue.insert(2);
        queue.insert(5);
        queue.insert(1);
        queue.insert(3);

        queue.delMax();
        queue.show();
    }
}
