package chapter_2._1elementary_sorts;

import edu.princeton.cs.algs4.In;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/14 20:28
 * <p>
 * 希尔排序
 */
public class Shell extends Example {

    /**
     * 根据网上总结自己实现的算法
     * <p>
     * 50000条数据排序100次，用时1.8s；
     * 100000条数据排序100次，用时4.8s；
     * 200000条数据排序100次，用时15.0s；
     */
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int gap = N / 2; gap > 0; gap /= 2) {  // gap：增量（步数），每次循环增量减少一倍，直至增量为1（此时对全部元素进行插入排序）完成排序。
            for (int i = 0; i < gap; i++) { // 把整体序列分为若干子序列。a[i]是每一组的第一个元素
                for (int j = i + gap; j < N; j += gap) { // 每间隔一个增量，获得一个该组的元素。
                    int tarIndex = j; // 目标元素索引，当前元素索引。
                    for (int k = tarIndex; k > i && less(a[k], a[k - gap]); k -= gap) { // 对子序列进行插入排序，将该元素与本组左边所有元素进行比较。
                        exch(a, k, k - gap);
                    }
                }
            }
        }
    }

    /**
     * 基于上面的改进版
     */
    public void sort2(Comparable[] a) {
        int N = a.length;
        for (int gap = N / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < N; i++) {
                for (int j = i - gap; j >= 0 && less(a[j + gap], a[j]); j -= gap) {
                    exch(a, j + gap, j);
                }
            }
        }
    }

    /**
     * 原文的算法，增量使用了递增序列，有时间再来理解。
     * <p>
     * 50000条数据排序100次，用时0.2s；
     * 100000条数据排序100次，用时4.8s；
     * 200000条数据排序100次，用时13.8s；
     */
    public void sort3(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            // 子数组插入排序
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        String[] a = new In().readAllStrings();
        new Shell().sort(a);
        assert isSorted(a);
        show(a);
    }
}

// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation -Xlint:unchecked -encoding utf-8  chapter_2\_1elementary_sorts\Shell.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_2/_1elementary_sorts/Shell < D:/IdeaProjects/Algorithms/algs4-data/tiny.txt
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_2/_1elementary_sorts/Shell < D:/IdeaProjects/Algorithms/src/chapter_2/_1elementary_sorts/data.txt


