package chapter_1.programming_model;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/04 10:46
 * <p>
 * 生成随机实数并格式化输出
 * 编译：javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib  -encoding utf-8 RandomSeq.java
 */
public class RandomSeq {
    public static void main(String[] args) {
        // 打印n个(lo,hi)之间的随机值
        int N = Integer.parseInt(args[0]);
        double lo = Double.parseDouble(args[1]);
        double hi = Double.parseDouble(args[2]);
        for (int i = 0; i < N; i++) {
            // 返回随机实数
            double x = StdRandom.uniform(lo, hi);
            StdOut.printf("%.2f\n", x);
        }
    }
}

//D:\IdeaProjects\Algorithms\src\chapter_1\programming_model\RandomSeq.java