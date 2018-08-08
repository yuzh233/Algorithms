package chapter_1.programming_model;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/04 21:27
 */
public class Ex16 {
    public static void main(String[] args) {
        String s = exR1(6);
        StdOut.println(s);//336446
    }

    public static String exR1(int n) {
        if (n <= 2) {
            return "";
        }
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }
}
