package chapter_1._4_analysis_of_algorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/11 12:34
 */
public class DoublingTest {

    public static double timeTrial(int N) {
        int MAX = 100000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            // Returns a random integer uniformly in [a, b).
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        StopWatch watch = new StopWatch();
        int cnt = ThreeSum.count(a);
        return watch.elapsedTime();
    }

    public static void main(String[] args) {
        for (int N = 250; ; N += N) {
            double time = timeTrial(N);
            StdOut.printf("%7d %5.1f\n", N, time);
        }
    }
}
