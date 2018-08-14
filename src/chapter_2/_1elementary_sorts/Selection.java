package chapter_2._1elementary_sorts;

import edu.princeton.cs.algs4.In;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/14 11:36
 * <p>
 * 选择排序
 */
public class Selection extends Example {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int minIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[minIndex])) {
                    minIndex = j; // 如果后续元素小于最小元素，把后续元素索引赋给最小元素索引。
                }
                exch(a, i, minIndex); // 交换原最小元素与新最小元素位置
            }
        }
    }

    public static void main(String[] args) {
        String[] a = new In().readAllStrings();
        new Selection().sort(a);
        assert isSorted(a); // 验证：确认排序后的算法是有序的，当序列元素相同时无法通过验证。
        show(a);
    }
}

// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation -Xlint:unchecked -encoding utf-8  chapter_2\_1elementary_sorts\Selection.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_2/_1elementary_sorts/Selection < D:/IdeaProjects/Algorithms/algs4-data/tiny.txt
