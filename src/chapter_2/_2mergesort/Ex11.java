package chapter_2._2mergesort;

import chapter_2._1elementary_sorts.Example;
import chapter_2._1elementary_sorts.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/20 22:24
 */
public class Ex11 extends Example {
    private static Comparable[] aux;

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    /**
     * 1.对小规模数组使用插入排序
     * 2.测试数组是否有序
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi - lo <= 15) {
            new Insertion().sort(a);
            return;
        }
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        if (less(a[mid + 1], a[mid])) {
            merge(a, aux, lo, mid, hi);
        }
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        Ex11 ex11 = new Ex11();
        Comparable[] a = new Comparable[10000];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform();
        }
        ex11.sort(a);
        StdOut.println(isSorted(a));
    }
}
