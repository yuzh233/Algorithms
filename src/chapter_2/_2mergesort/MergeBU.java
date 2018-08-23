package chapter_2._2mergesort;

import chapter_2._1elementary_sorts.Example;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/21 10:22
 */
public class MergeBU extends Example {

    /**
     * 自底向上归并排序
     */
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) { // 控制子数组大小呈倍数递增
            /**
             * 遍历每个子数组
             *  lo                                  每个子数组的第一个元素
             *  lo < N - sz                         控制最后一个子数组的开头
             *  lo = lo + sz + sz                   跳到下一个子数组开头
             *  Math.min(lo + sz + sz - 1, N - 1)   最后一个子数组的大小有可能不是sz的整数倍，lo + sz + sz - 1可能会出现数组越界。
             */
            for (int lo = 0; lo < N - sz; lo = lo + sz + sz) {
                int mid = lo + sz - 1;
                int hi = Math.min(lo + sz + sz - 1, N - 1);
                merge(a, aux, lo, mid, hi);
            }
        }
        assert isSorted(a);
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        MergeBU mergeBU = new MergeBU();
        Comparable[] a = new Comparable[10000];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform();
        }
        mergeBU.sort(a);
        show(a);
        StdOut.println(isSorted(a));
    }
}
