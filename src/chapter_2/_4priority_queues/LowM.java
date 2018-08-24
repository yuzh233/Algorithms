package chapter_2._4priority_queues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/24 15:48\\
 * <p>
 * 从输入流中返回M行最小的值
 */
public class LowM {
    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
//        MaxPQ<Transaction> pq = new MaxPQ4Array<>();
        MaxPQ<Transaction> pq = new MaxPQ4Linked<>();
        while (StdIn.hasNextLine()) {
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M) pq.delMax();
        }

        while (!pq.isEmpty()) {
            StdOut.println(pq.delMax());
        }
    }
}

// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation -Xlint:unchecked -encoding utf-8  chapter_2\_4priority_queues\LowM.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_2/_4priority_queues/LowM 5 < D:/IdeaProjects/Algorithms/algs4-data/tinyBatch.txt

