package chapter_1.programming_model;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/04 14:21
 * <p>
 * 输出：10000
 */
public class Ex7_c {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i < 1000; i *= 2) {
            for (int j = 0; j < 1000; j++) {
                sum++;
            }
        }
        StdOut.println(sum);
    }
}
