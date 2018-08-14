package chapter_2._1elementary_sorts;

import chapter_1._4analysis_of_algorithms.StopWatch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/14 13:53
 * <p>
 * 两种排序算法速度对比的用例
 */
public class SortCompare {
    public static double time(String alg, Comparable[] a) {
        StopWatch watch = new StopWatch();
        if (alg.equals("Insertion")) {
            new Insertion().sort(a);
        }
        if (alg.equals("Selection")) {
            new Selection().sort(a);
        }
        if (alg.equals("Shell")) {
            new Shell().sort(a);
        }
        if (alg.equals("Merge")) {
//            new Merge().sort(a);
        }
        if (alg.equals("Quick")) {
//            new Quick().sort(a);
        }
        if (alg.equals("Heap")) {
//            new Heap().sort(a);
        }
        return watch.elapsedTime();
    }

    //使用alg算法将长度为N的数组排序T次
    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N]; // 目标数组
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform(); // 生成随机值
            }
            total += time(alg, a); // 计算T次时间总和
        }
        show(a);
        return total;
    }

    public static void show(Double[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T); // 算法1的总时间
        StdOut.printf("the %s algorithm takes %.1f seconds.\n", alg1, t1);
        double t2 = timeRandomInput(alg2, N, T); // 算法2的总时间
        StdOut.printf("the %s algorithm takes %.1f seconds.\n", alg2, t2);
    }
}


// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation -Xlint:unchecked -encoding utf-8  chapter_2\_1elementary_sorts\SortCompare.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_2/_1elementary_sorts/SortCompare Insertion Selection 10000 100
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_2/_1elementary_sorts/SortCompare Shell Insertion 10000 100

