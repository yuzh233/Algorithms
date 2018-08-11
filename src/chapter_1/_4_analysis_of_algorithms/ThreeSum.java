package chapter_1._4_analysis_of_algorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/10 19:13
 * <p>
 * 第一个测试程序运行时间的用例：统计一个文件中所有和为0的三整数元祖的数量（假设整数不会溢出）
 */
public class ThreeSum {

    public static int count(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        // int[] a = In.readInts(args[0]); 此行被替换为下面
        /**
         * 1Kints.txt： 70 time:0.43
         * 2Kints.txt： 528 time:2.813
         * 4Kints.txt： 4039 time:time:18.348
         * 8Kints.txt： 32074 time:141.934
         */
        int[] a = new In("D:\\IdeaProjects\\Algorithms\\algs4-data\\4Kints.txt").readAllInts();
        StopWatch watch = new StopWatch();
        StdOut.println(count(a));
        StdOut.println("time:" + watch.elapsedTime());
    }
}
