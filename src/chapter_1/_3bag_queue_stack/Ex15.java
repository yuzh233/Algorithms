package chapter_1._3bag_queue_stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/07 15:30
 * Queue的用例：接收命令行参数K，从标准输入中读取倒数第K个字符串。
 */
public class Ex15 {
    public static void main(String[] args) {
        LinkedQueue<String> queue = new LinkedQueue<>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            queue.enqueue(str);
        }
        int index = queue.size() - k;
        int i = 0;
        Iterator iterator = queue.iterator();
        while (iterator.hasNext()) {
            if (i == index) {
                StdOut.println("the last but " + (k - 1) + ": " + iterator.next()); //倒数第一：the last / 倒数第二：the last but 1 ...
                break;
            }
            iterator.next();
            i++;
        }
    }
}

//javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation  -Xlint:unchecked -encoding utf-8  chapter_1\_3bag_queue_stack\Ex15.java
//java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_1/_3bag_queue_stack/Ex15 2 < D:/IdeaProjects/Algorithms/src/chapter_1/_3bag_queue_stack/Ex15.txt