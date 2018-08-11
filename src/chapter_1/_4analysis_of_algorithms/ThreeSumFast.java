package chapter_1._4analysis_of_algorithms;

import chapter_1._1programming_model.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/11 11:45
 * <p>
 * 解决3-sum问题的快速算法
 */
public class ThreeSumFast {

    public static int count(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (BinarySearch.rank(-a[i] - a[j], a) > j) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        /**
         * 4Kints.txt： 619 time:time:11.699
         */
        int[] a = new In("D:\\IdeaProjects\\Algorithms\\algs4-data\\4Kints.txt").readAllInts();
        StopWatch watch = new StopWatch();
        StdOut.println(count(a));
        StdOut.println("time:" + watch.elapsedTime());
    }
}
