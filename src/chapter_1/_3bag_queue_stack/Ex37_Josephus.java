package chapter_1._3bag_queue_stack;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/09 12:41
 * <p>
 * Josephus生存游戏：N个人围成一圈 [0,N-1] ，从第一个开始报数，报到 M(M>1,从1开始) 的人被杀死，直到留下最后一个人。
 */
public class Ex37_Josephus {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        // 想挑战的排好队，一个一个来！
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        for (int i = 0; i < N; i++) {
            queue.enqueue(i);
        }
        // 死亡名单
        LinkedQueue<Integer> deathList = new LinkedQueue<>();
        for (int i = 1; i <= M; i++) {
            if (i == M) {
                deathList.enqueue(queue.dequeue());
                i = 0;
            } else {
                if (queue.size() == 1) {
                    break;
                } else {
                    // 你暂时不用死，到后面重新排队去~
                    queue.enqueue(queue.dequeue());
                }
            }
        }
        Iterator iterator = deathList.iterator();
        while (iterator.hasNext()) {
            StdOut.print(iterator.next() + " ");
        }
        StdOut.println("survival: " + queue.dequeue());
    }
}


//javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation  -Xlint:unchecked -encoding utf-8  chapter_1\_3bag_queue_stack\Ex37_Josephus.java
//java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_1/_3bag_queue_stack/Ex37_Josephus 7 2