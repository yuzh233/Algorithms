package chapter_1._4_analysis_of_algorithms;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/11 12:41
 * <p>
 * 倍率实验
 */
public class DoublingRatio {
    public static void main(String[] args) {
        double prev = DoublingTest.timeTrial(125);
        for (int N = 250; ; N += N) {
            double time = DoublingTest.timeTrial(N);
            StdOut.printf("%6d %7.1f ", N, time);
            StdOut.printf("%5.1f\n", time / prev);
            prev = time;
        }
    }
}
