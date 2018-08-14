package chapter_2._1elementary_sorts;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/14 11:09
 * <p>
 * 排序类模板
 */
public abstract class Example {

    /**
     * 具体排序算法实现
     *
     * @param a
     */
    public abstract void sort(Comparable[] a);

    /**
     * 对元素进行比较
     *
     * @param first
     * @param second
     * @return first < second ? true : false
     */
    public static boolean less(Comparable first, Comparable second) {
        return first.compareTo(second) < 0;
    }

    /**
     * 把两个元素交换位置
     *
     * @param a
     * @param i
     * @param j
     */
    public static void exch(Comparable[] a, int i, int j) {
        Comparable tem = a[i];
        a[i] = a[j];
        a[j] = tem;
    }

    /**
     * 返回序列是否有序（asc）
     *
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                // 后面的元素 < 前面的元素 不是升序排列 返回false
                return false;
            }
        }
        return true;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
}
