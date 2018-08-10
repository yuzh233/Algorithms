package chapter_1._1programming_model;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/04 14:56
 */
public class Ex13 {
    public static void main(String[] args) {
        int[][] arr = {
                {23, 42, 53, 64, 23},
                {65, 34, 78, 57, 90},
                {57, 93, 36, 27, 37},
                {36, 36, 53, 53, 58},
                {95, 54, 27, 96, 85}
        };

        StdOut.println("before");
        for (int i = 0; i < arr.length; i++) { // 行
            for (int j = 0; j < arr[i].length; j++) { // 列
                StdOut.printf("%d\t", arr[i][j]);
            }
            StdOut.println();
        }
        StdOut.println("after");
        // 交换行和列，该算法有一个不足：每一行长度必须一致。
        // 外循环：列  内循环：行
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = arr.length - 1; j > -1; j--) {
                StdOut.printf("%d\t", arr[j][i]);
            }
            StdOut.println();
        }
    }
}
