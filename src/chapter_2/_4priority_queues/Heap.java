package chapter_2._4priority_queues;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/26 16:41
 * <p>
 * 堆排序
 */
public class Heap {
    public void sort(Comparable[] a) {
        int n = a.length;
        // 构造堆
        sinkGenerationHead(a, n);
//        swimGenerationHead(a, n);
        // 堆排序
        while (n > 1) {
            exch(a, 1, n--);
            sink(a, 1, n);
        }
    }

    //下沉生成大顶堆（最优）
    public void sinkGenerationHead(Comparable[] a, int n) {
        for (int i = n / 2; i >= 1; i--) { // 从右到左
            sink(a, i, n);
        }
    }

    // 上浮生成大顶堆
    public void swimGenerationHead(Comparable[] a, int n) {
        for (int k = 0; k < n; k++) { // 必须是从左到右
            swim(a, k, n);
        }
    }

    public void sink(Comparable[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(a, j, j + 1))
                j++;
            if (!less(a, k, j))
                break;
            exch(a, k, j);
            k = j;
        }
    }

    public void swim(Comparable[] a, int k, int n) {
        while (k > 1 && less(a, k / 2, k)) {
            exch(a, k / 2, k);
            k = k / 2;
        }
    }

    private boolean less(Comparable[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    private void exch(Comparable[] a, int i, int j) {
        Comparable tem = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = tem;
    }

    public static void main(String[] args) {
        Comparable[] a = new Comparable[]{2, 0, 5, 7, 9, 8, 3, 1, 4, 6};
        Heap heap = new Heap();
        heap.sort(a);
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
    }
}
