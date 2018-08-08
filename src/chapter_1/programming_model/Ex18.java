package chapter_1.programming_model;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/04 21:56
 */
public class Ex18 {
    public static void main(String[] args) {
        int mystery = mystery(2, 25);
        StdOut.println(mystery);
    }

    public static int mystery(int a, int b) {
        if (b == 0) {
            return 0;
        }
        if (b % 2 == 0) {
            return mystery(a + a, b / 2);
        }
        return mystery(a + a, b / 2) + a;
    }
}
