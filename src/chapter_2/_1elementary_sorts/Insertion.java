package chapter_2._1elementary_sorts;

import edu.princeton.cs.algs4.In;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/14 12:03
 * <p>
 * 插入排序
 */
public class Insertion extends Example {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            // 将当前元素 a[i] 与 其左边的所有元素对比、交换位置
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                // 后面的元素比前面的元素小才进行排序
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        String[] a = new In().readAllStrings();
        new Insertion().sort(a);
        assert isSorted(a);
        show(a);
    }
}

// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation -Xlint:unchecked -encoding utf-8  chapter_2\_1elementary_sorts\Insertion.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_2/_1elementary_sorts/Insertion < D:/IdeaProjects/Algorithms/algs4-data/tiny.txt
