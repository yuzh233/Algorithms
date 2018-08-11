package chapter_1._3bag_queue_stack;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/09 19:38
 * <p>
 * 可连接的队列、栈
 */
public class Ex47 {
    public static void main(String[] args) {
        // 基于单向链式结构的可连接栈
       /* LinkedStack<String> stack1 = new LinkedStack<>();
        LinkedStack<String> stack2 = new LinkedStack<>();
        stack1.push("11");
        stack1.push("22");
        stack1.push("33");
        stack1.push("44");
        stack2.push("55");// 连接在stack1的头节点
        stack2.push("66");
        stack2.push("77");
        stack2.push("88");
        stack1.catenation(stack2);

        Iterator<String> iterator = stack1.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
        StdOut.println("-------------");
        Iterator<String> iterator2 = stack2.iterator();
        while (iterator2.hasNext()) {
            StdOut.println(iterator2.next());
        }*/

        // 基于双向链式结构的可连接栈（容易实现一些）
        /*Ex31_DoubleLinkedStack<String> stack1 = new Ex31_DoubleLinkedStack<>();
        Ex31_DoubleLinkedStack<String> stack2 = new Ex31_DoubleLinkedStack<>();
        stack1.push("11");
        stack1.push("22");
        stack1.push("33");
        stack1.push("44");
        stack2.push("55");// 连接在stack1的头节点
        stack2.push("66");
        stack2.push("77");
        stack2.push("88");
        stack1.catenation(stack2);

        Iterator<String> iterator = stack1.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
        StdOut.println("-------------");
        Iterator<String> iterator2 = stack2.iterator();
        while (iterator2.hasNext()) {
            StdOut.println(iterator2.next());
        }*/

        // 基于单向链式结构的可连接队列
        LinkedQueue<String> queue1 = new LinkedQueue<>();
        LinkedQueue<String> queue2 = new LinkedQueue<>();
        queue1.enqueue("11");
        queue1.enqueue("22");
        queue1.enqueue("33");
        queue1.enqueue("44");
        queue2.enqueue("55");
        queue2.enqueue("66");
        queue2.enqueue("77");
        queue2.enqueue("88");
        queue1.catenation(queue2);

        Iterator<String> iterator = queue1.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
        StdOut.println("-------------");
        Iterator<String> iterator2 = queue2.iterator();
        while (iterator2.hasNext()) {
            StdOut.println(iterator2.next());
        }
    }
}
