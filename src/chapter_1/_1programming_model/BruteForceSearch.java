package chapter_1._1programming_model;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/04 12:57
 * 暴力查找
 */
public class BruteForceSearch {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist) < 0) {
                StdOut.println(key);
            }
        }
        long end = System.currentTimeMillis();
        StdOut.println("time->:" + (end - start));
    }

    public static int rank(int key, int[] arr) {
        // 使用lo和hi变量保证key一定在arr[lo...hi]中
        int lo = 0;
        int hi = arr.length - 1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
}
