package chapter_1._3bag_queue_stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/05 15:54
 * 队列的典型用例
 */
public class QueueDemo {
    public static int[] readInts(String name) {
        In in = new In(name);
        Queue<Integer> q = new Queue<>();
        while (!in.isEmpty()) {
            q.enqueue(in.readInt());
        }

        int N = q.size();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = q.dequeue();
        }
        return a;
    }

    public static void main(String[] args) {
        readInts(args[0]);
    }
}
