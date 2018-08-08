package chapter_1.bag_queue_stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/06 21:48
 * 从标准输入流中读取一串缺少左括号的表达式，要求返回补全括号之后的中序表达式。
 * 比如输入：1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) ) 输出：( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
 * <p>
 * 思路：1. 操作数和拼接后的表达式放入操作数栈，操作符放入操作符栈
 *      2. 遇到操作数，操作数压栈
 *      3. 遇到操作符，操作符压栈
 *      4. 遇到")"，弹出两个操作数，弹出一个操作符，拼接表达式并压入操作数栈。
 *      5. 最终，操作数栈只剩下一个操作符就是需要的结果表达式。
 */
public class Ex9 {
    public static void main(String[] args) {
        LinkedStack<String> ops = new LinkedStack<>();
        LinkedStack<String> vls = new LinkedStack<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if ("+".equals(s)) {
                ops.push(s);
            } else if ("-".equals(s)) {
                ops.push(s);
            } else if ("*".equals(s)) {
                ops.push(s);
            } else if ("/".equals(s)) {
                ops.push(s);
            } else if (")".equals(s)) {
                // 一个操作符弹栈、两个操作数弹栈、拼接后的表达式压入操作数栈。
                String op = ops.pop();
                String num1 = vls.pop();
                String num2 = vls.pop();
                String express = "( " + num2 + " " + op + " " + num1 + " )";
                vls.push(express);
            } else {
                // 操作数压栈
                vls.push(s);
            }
        }
        StdOut.println(vls.pop());
    }
}


//javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation  -Xlint:unchecked -encoding utf-8  chapter_1\bag_queue_stack\Ex9.java
//java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_1/bag_queue_stack/Ex9 < D:/IdeaProjects/Algorithms/src/chapter_1/bag_queue_stack/express.txt