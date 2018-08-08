package chapter_1.programming_model;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/04 14:16
 * <p>
 * 输出：
 * 5.00000
 * 3.40000
 * 3.02353
 * 3.00009
 */
public class Ex7_a {
    public static void main(String[] args) {
        double t = 9.0;
        while (Math.abs(t - 9.0 / t) > .001) {
            t = (9.0 / t + t) / 2.0;
            StdOut.printf("%.5f\n", t);
        }
    }
}
