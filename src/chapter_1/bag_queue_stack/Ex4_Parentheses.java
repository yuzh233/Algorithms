package chapter_1.bag_queue_stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/06 20:48
 * 从标准输入流中输入一串文本，判定其中的括号是否匹配
 */
public class Ex4_Parentheses {
    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        boolean re = true;
        while (!StdIn.isEmpty()) {
            String ops = StdIn.readString();
            if (ops.equals(")")) {
                String s = stack.pop();
                if (!s.equals("(")) {
                    re = false;
                    break;
                }
            } else if (ops.equals("]")) {
                String s = stack.pop();
                if (!s.equals("[")) {
                    re = false;
                    break;
                }
            } else if (ops.equals("}")) {
                String s = stack.pop();
                if (!s.equals("{")) {
                    re = false;
                    break;
                }
            } else {
                stack.push(ops);
            }
        }
        Iterator<String> iterator = stack.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
        StdOut.println(re);
    }
}

//[ ( ) ] { } { [ ( ) ( ) ] ( ) }      /     [ ( ] )
//javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation  -Xlint:unchecked -encoding utf-8  chapter_1\bag_queue_stack\Ex4_Parentheses.java
//java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_1/bag_queue_stack/Ex4_Parentheses < D:/IdeaProjects/Algorithms/src/chapter_1/bag_queue_stack/parentheses.txt