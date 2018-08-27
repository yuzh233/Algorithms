package chapter_2._4priority_queues;

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/27 19:34
 * <p>
 * 最小优先队列
 */
public class MinPQ<Key extends Comparable> {
    private Key[] pq;
    private int N;

    MinPQ() {
        this(1);
    }

    MinPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public void insert(Key v) {
        if (N == pq.length - 1) resize(pq.length * 2);
        pq[++N] = v;
        swim(N);
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    public Key delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key min = pq[1];
        exch(N--, 1);
        pq[N + 1] = null;
        sink(1);
        if (N > 0 && (pq.length - 1) / 4 == N) resize(pq.length / 2);
        return min;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (k * 2 <= N) {
            int j = k * 2;
            if (j < N && less(j + 1, j)) // j < N 保证能访问到最后一个节点
                j++;
            if (less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int a, int b) {
        return pq[a].compareTo(pq[b]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void resize(int max) {
        Key[] tem = (Key[]) new Comparable[max];
        for (int i = 1; i < N + 1; i++) {
            tem[i] = pq[i];
        }
        pq = tem;
    }

    public void show() {
        for (int i = 1; i <= N; i++) {
            StdOut.print(pq[i] + " ");
        }
    }

    public static void main(String[] args) {
        MinPQ<Integer> pq = new MinPQ<>();
        pq.insert(5);
        pq.insert(9);
        pq.insert(1);
        pq.insert(2);
        pq.insert(7);

        StdOut.println("delMin: " + pq.delMin());
        StdOut.println("delMin: " + pq.delMin());
        StdOut.println("delMin: " + pq.delMin());
        StdOut.println("delMin: " + pq.delMin());
        StdOut.println("min: " + pq.min());
    }
}
