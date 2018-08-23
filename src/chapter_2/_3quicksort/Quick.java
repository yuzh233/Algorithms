package chapter_2._3quicksort;

import chapter_2._1elementary_sorts.Example;
import chapter_2._1elementary_sorts.Insertion;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/23 13:52
 */
public class Quick extends Example {
    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        // if (hi <= lo) return;
        // 对于小数组，改用插入排序。
        if (hi <= lo + 15) {
            new Insertion().sort(a);
            return;
        }
        int j = partition(a, lo, hi); // 切分
        sort(a, lo, j - 1); // 左半边排序
        sort(a, j + 1, hi); // 右半边排序
    }

    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1; // 左右扫描的指针
        Comparable v = a[lo]; // 切分的元素
        while (true) {
            while (less(a[++i], v)) { // 指针 i 从左往右扫描大于v的值
                if (i == hi) break;
            }
            while (less(v, a[--j])) { // 指针 j 从右往左扫描小于v的值
                if (j == lo) break;
            }
            if (i >= j) break; // 为什么 i >= j 退出外循环？
            exch(a, i, j); // 小值放左边，大值放右边。
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Comparable[] a = new Comparable[]{2, 0, 5, 7, 9, 8, 3, 1, 4, 6};
        new Quick().sort(a);
        show(a);
    }
}
