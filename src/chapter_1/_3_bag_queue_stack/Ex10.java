package chapter_1._3_bag_queue_stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/07 13:28
 * <p>
 * 将算术表达式由中序表达式转为后序表达式
 * 例如：
 * ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )  ->  12+34-56-** （输出的结果最好用空格隔开）
 * ( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )  ->  234+56**+ （输出的结果最好用空格隔开）
 * <p>
 * 什么是后序表达式？
 * https://www.cnblogs.com/huxianglin/p/5863129.html
 * 实现参考：
 * https://blog.csdn.net/zsuguangh/article/details/6280863
 * https://blog.csdn.net/panbaop/article/details/51815421
 * <p>
 * 算法逻辑：
 * 1. 一个操作符栈、一个后序表达式队列。
 * 2. 不关心左右大括号，也不关心栈内栈外运算符优先级，只将")"看作弹栈的时机。
 * 3. 遇到操作符，操作符压栈；遇到操作值，操作值入队列。
 * 4. 遇到")"弹出一个操作符进入队列。
 * 5. 队列最终拼凑成后序表达式，遍历打印。
 */
public class Ex10 {
    public static void main(String[] args) {
        LinkedStack<String> ops = new LinkedStack<>();
        LinkedQueue<String> expressQueue = new LinkedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if ("(".equals(s)) {
            } else if ("+".equals(s)) {
                ops.push(s);
            } else if ("-".equals(s)) {
                ops.push(s);
            } else if ("*".equals(s)) {
                ops.push(s);
            } else if ("/".equals(s)) {
                ops.push(s);
            } else if (")".equals(s)) {
                String ops1 = ops.pop();
                expressQueue.enqueue(ops1+" ");
            } else {
                expressQueue.enqueue(s+" ");
            }
        }
        Iterator iterator = expressQueue.iterator();
        while (iterator.hasNext()) {
            StdOut.print(iterator.next());
        }
    }

    //原文作者标准答案：
    /*
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if      (s.equals("+")) stack.push(s);
            else if (s.equals("*")) stack.push(s);
            else if (s.equals(")")) StdOut.print(stack.pop() + " ");
            else if (s.equals("(")) StdOut.print("");
            else                    StdOut.print(s + " ");
        }
        StdOut.println();
    }
     */
}

//javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation  -Xlint:unchecked -encoding utf-8  chapter_1\_3_bag_queue_stack\Ex10.java
//java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_1/_3_bag_queue_stack/Ex10 < D:/IdeaProjects/Algorithms/src/chapter_1/_3_bag_queue_stack/Ex10.txt