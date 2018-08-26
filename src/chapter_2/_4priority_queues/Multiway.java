package chapter_2._4priority_queues;

import edu.princeton.cs.algs4.In;
//import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/25 23:39
 * <p>
 * 索引优先队列的用例
 */
public class Multiway {
    public static void main(String[] args) {
        int N = args.length;
        In[] streams = new In[N]; // 流对象数组
        for (int i = 0; i < N; i++) {
            streams[i] = new In(args[i]); // 以文件名作为参数构建输入流
        }
        merge(streams); // 合并所有输入流
    }

    private static void merge(In[] streams) {
        int N = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<>(N);
//        IndexMinPQ<String> pq = new IndexMinPQ<>(N);

        for (int i = 0; i < N; i++) {
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }

        while (!pq.isEmpty()) {
            StdOut.print(pq.min()+" ");
            int i = pq.delMin();

            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }
    }
}


// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation -Xlint:unchecked -encoding utf-8  chapter_2\_4priority_queues\Multiway.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_2/_4priority_queues/Multiway D:\IdeaProjects\Algorithms\algs4-data\m1.txt D:\IdeaProjects\Algorithms\algs4-data\m2.txt D:\IdeaProjects\Algorithms\algs4-data\m3.txt
