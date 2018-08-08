package chapter_1.bag_queue_stack;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/05 16:35
 * 栈的典型用例：双栈算术表达式求值算法
 */
public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        while (!StdIn.isEmpty()) {
            // 循环读取每一个操作符，如果是运算符则压入运算符栈
            String s = StdIn.readString();
            if (s.equals("(")) {
            } else if (s.equals("+")) {
                ops.push(s);
            } else if (s.equals("-")) {
                ops.push(s);
            } else if (s.equals("*")) {
                ops.push(s);
            } else if (s.equals("/")) {
                ops.push(s);
            } else if (s.equals("sqrt")) {
                ops.push(s);
            } else if (s.equals(")")) {
                // 如果运算符为 ） ，弹出运算符和操作数，计算结果并压入操作数栈
                String op = ops.pop();
                double v = vals.pop(); // 弹出栈顶的操作数。弹出栈元素是在栈中删除了的
                if (op.equals("+")) {
                    v = vals.pop() + v; // 再弹出一个栈顶操作数，与前面弹出的栈顶操作数（v）做运算并重新赋值给变量v
                } else if (op.equals("-")) {
                    v = vals.pop() - v; // 第二次弹出的操作数在前，第一次弹出的操作数在后。因为先进栈的操作数后弹出，后进栈的元素先弹出
                } else if (op.equals("*")) {
                    v = vals.pop() * v;
                } else if (op.equals("/")) {
                    v = vals.pop() / v;
                } else if (op.equals("sqrt")) {
                    v = Math.sqrt(v);
                }
                vals.push(v); // 运算后的值入栈，进行运算的两个操作数被弹出（删除）了。
            } else {
                // 如果既不是运算符也不是括号，则就是数字，将其压入数值栈。
                vals.push(Double.parseDouble(s));
            }
        }
        StdOut.println(vals.pop());
    }
}


//javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation  -encoding utf-8  chapter_1\bag_queue_stack\Evaluate.java
//java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib  chapter_1\bag_queue_stack\Evaluate
