package chapter_2._5sorting_application;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/27 23:27
 * <p>
 * 快速排序倒序
 */
public class QuickSortDesc {

    public static void sort(Comparable[] tasks) {
        StdRandom.shuffle(tasks);
        sort(tasks, 0, tasks.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (!less(a[++i], v)) {
                if (i == hi) break;
            }
            while (!less(v, a[--j])) {
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void show(Comparable[] a) {
        for (int j = 0; j < a.length; j++) {
            StdOut.println(a[j]);
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tem = a[i];
        a[i] = a[j];
        a[j] = tem;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
