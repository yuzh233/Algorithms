package chapter_1._3bag_queue_stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/07 14:22
 * 根据将第十题的中序表达式利用管道作为输入流传给本用例计算表达式的结果
 * <p>
 * 比较简单，只需要将操作符作为弹栈时机
 * <p>
 * 2 3 4 + 5 6 * * + = 211
 * 1 2 + 3 4 - 5 6 - * * = 3
 */
public class Ex11_EvaluatePostfix {
    public static void main(String[] args) {
        LinkedStack<Double> vls = new LinkedStack<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if ("+".equals(s)) {
                Double num1 = vls.pop();
                Double num2 = vls.pop();
                vls.push(num2 + num1);
            } else if ("-".equals(s)) {
                Double num1 = vls.pop();
                Double num2 = vls.pop();
                vls.push(num2 - num1);
            } else if ("*".equals(s)) {
                Double num1 = vls.pop();
                Double num2 = vls.pop();
                vls.push(num2 * num1);
            } else if ("/".equals(s)) {
                Double num1 = vls.pop();
                Double num2 = vls.pop();
                vls.push(num2 / num1);
            } else if ("%".equals(s)) {
                Double num1 = vls.pop();
                Double num2 = vls.pop();
                vls.push(num2 % num1);
            } else {
                vls.push(Double.parseDouble(s));
            }
        }
        StdOut.println(vls.pop());
    }
}

//javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation  -Xlint:unchecked -encoding utf-8  chapter_1\_3bag_queue_stack\Ex11_EvaluatePostfix.java
//利用文本作为输入流：java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_1/_3bag_queue_stack/Ex11_EvaluatePostfix < D:/IdeaProjects/Algorithms/src/chapter_1/_3bag_queue_stack/Ex11.txt
//利用管道作为输入流：java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_1/_3bag_queue_stack/Ex11_EvaluatePostfix | java ....../Ex10