package chapter_2._2mergesort;

import chapter_2._1elementary_sorts.Example;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/20 17:37
 */
public class Merge extends Example {
    private static Comparable aux[]; // 辅助数组，用于合并操作。

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid); // 左半边排序
        sort(a, mid + 1, hi); // 右半边排序
        merge(a, aux, lo, mid, hi); // 合并子数组
    }

    /**
     * 将两个`有序`子数组合并为一个有序数组
     *
     * @param a   array 目标数组
     * @param lo  low 第一个元素索引
     * @param mid middle 中间元素索引
     * @param hi  high 最后一个元素索引
     */
    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int le = lo;
        int ri = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (le > mid) {
                a[k] = aux[ri++]; // 左边元素用尽，将右边元素一个一个放入a[]
            } else if (ri > hi) {
                a[k] = aux[le++]; // 右边元素用尽，将左边元素一个一个放入a[]
            } else if (less(aux[ri], aux[le])) {
                a[k] = aux[ri++]; // 右边元素小于左边元素，右边元素放入a[]，右边元素索引+1
            } else {
                a[k] = aux[le++]; // 左边元素小于右边元素，左边元素放入a[]，左边元素索引+1
            }
        }
    }

    public static void main(String[] args) {
        // test merge()
//        Comparable[] a = new Comparable[]{5, 6, 7, 8, 9, 0, 1, 2, 3, 4};
//        merge(a, 0, 4, a.length - 1);

        // test sort()
        Comparable[] a = {5, 7, 2, 1, 4, 8, 6, 9, 0, 3};
        new Merge().sort(a);
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
    }
}
