package chapter_1._4_analysis_of_algorithms;

import chapter_1._1programming_model.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/11 11:45
 * <p>
 * 解决2-sum问题的快速算法
 */
public class TwoSumFast {

    // 线性对数级别
    public static int count(int[] a) {
        StopWatch watch = new StopWatch();
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (BinarySearch.rank(-a[i], a) > i) {
                cnt++;
            }
        }
        StdOut.println(watch.elapsedTime());
        return cnt;
    }

    // 平方级别
    public static int slow(int[] a) {
        StopWatch watch = new StopWatch();
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (a[i] + a[j] == 0) {
                    cnt++;
                }
            }
        }
        StdOut.println(watch.elapsedTime());
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = new In("D:\\IdeaProjects\\Algorithms\\algs4-data\\8Kints.txt").readAllInts();
//        StdOut.println(slow(a)); // 19s
        StdOut.println(count(a)); // 0.156s
    }
}
