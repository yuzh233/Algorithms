package chapter_1._1programming_model;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/04 12:57
 */
public class BinarySearch {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // Reads all integers from a file and returns them as an array of integers. argument：filename
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            // Reads the next token from standard input, parses it as an integer,
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
        for (int i = 0; i < hi; i++) {
            // 取中间值索引，当查找的范围在左边，lo始终为0，当查找的范围在右边，中间值索引就是起始值索引+前后折半的值
            int mid = lo + (hi - lo) / 2;
            // 小于中间值，查找范围缩小到左边
            if (key < arr[i]) {
                hi = mid - 1;
            }
            // 大于中间值，查找范围缩小到右边
            if (key > arr[i]) {
                lo = mid + 1;
            } else {
                return i;
            }
        }
        return -1;
    }
}
