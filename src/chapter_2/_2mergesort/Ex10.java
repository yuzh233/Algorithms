package chapter_2._2mergesort;

import chapter_2._1elementary_sorts.Example;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/21 22:14
 */
public class Ex10 extends Example {

    @Override
    public void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid); // 左半边排序
        sort(a, aux, mid + 1, hi); // 右半边排序
        merge(a, aux, lo, mid, hi); // 合并子数组
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo;
        int j = hi;

        // 左半边顺序复制到aux[]
        for (int k = lo; k <= mid; k++) {
            aux[k] = a[k];
        }

        // 右半边逆序复制到aux[]
        for (int k = mid + 1; k <= hi; k++) {
            aux[k] = a[hi + mid + 1 - k];
        }

        for (int k = lo; k <= hi; k++) {
            if (less(aux[j], aux[i])) {
                a[k] = aux[j--];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        // test merge()
//        Comparable[] a = new Comparable[]{5, 6, 7, 8, 9, 0, 1, 2, 3, 4};
//        merge(a, new Comparable[a.length], 0, 4, a.length - 1);
//        show(a);

        // test sort()
        Comparable[] a = {5, 7, 2, 1, 4, 8, 6, 9, 0, 3};
        new Ex10().sort(a);
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
    }

}
