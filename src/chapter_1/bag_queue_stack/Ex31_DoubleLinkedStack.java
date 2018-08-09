package chapter_1.bag_queue_stack;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.Objects;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/08 21:37
 * 双向链表结构
 */
public class Ex31_DoubleLinkedStack<Item> implements Iterable {
    private DoubleNode first;
    private DoubleNode last;
    private int N;

    @Override
    public Iterator iterator() {
        return new ListIterator<Item>();
    }

    private class ListIterator<Item> implements Iterator {
        private DoubleNode currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public DoubleNode<Item> next() {
            DoubleNode node = currentNode;
            currentNode = currentNode.next;
            return node;
        }
    }

    private static class DoubleNode<Item> {
        private Item item;
        private DoubleNode<Item> pre;
        private DoubleNode<Item> next;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DoubleNode<?> that = (DoubleNode<?>) o;
            return Objects.equals(item, that.item) &&
                    Objects.equals(pre, that.pre) &&
                    Objects.equals(next, that.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(item, pre, next);
        }

        @Override
        public String toString() {
            return "DoubleNode{" +
                    "item=" + item +
                    '}';
        }
    }

    /**
     * 表头压入节点
     *
     * @param item
     */
    public void push(Item item) {
        DoubleNode<Item> oldFirst = first;
        first = new DoubleNode();
        first.item = item;
        first.next = oldFirst;
        if (N != 0) {
            oldFirst.pre = first;
        }
        if (N == 0) {
            // last只需赋值一次即可
            last = first;
        }
        N++;
    }

    /**
     * 表头弹出节点
     *
     * @return
     */
    public DoubleNode<Item> pop() {
        DoubleNode<Item> oldFirst = first;
        first = oldFirst.next;
        if (first == null) {
            last = first;
        } else {
            first.pre = null;
        }
        N--;
        return oldFirst;
    }

    /**
     * 表尾压入节点
     *
     * @param item
     */
    public void pushLast(Item item) {
        if (N == 0) {
            push(item);
        }
        DoubleNode<Item> oldLast = last;
        last = new DoubleNode<Item>();
        last.item = item;
        last.next = null;
        last.pre = oldLast;
        oldLast.next = last;
        N++;
    }

    /**
     * 表尾弹出节点
     */
    public DoubleNode<Item> popLast() {
        DoubleNode<Item> oldLast = null;
        if (N == 1) {
            // 只剩下一个节点，直接栈顶弹出
            return pop();
        }
        if (N > 1) {
            oldLast = last;
            last = oldLast.pre;
            last.next = null;
            N--;
        }
        return oldLast;
    }

    /**
     * 指定节点之前压入新节点
     *
     * @param target 被插入的目标节点
     * @param source 插入的节点
     */
    public void pushBefore(DoubleNode<Item> target, DoubleNode<Item> source) {
        if (target.equals(first)) {
            push(source.item);
            return;
        }
        Iterator iterator = iterator();
        while (iterator.hasNext()) {
            DoubleNode<Item> currentNode = (DoubleNode<Item>) iterator.next();
            if (currentNode.equals(target)) {
                source.next = currentNode;
                source.pre = currentNode.pre;
                currentNode.pre.next = source;
                N++;
            }
        }
    }

    /**
     * 指定节点之后压入新节点
     *
     * @param target 被插入的目标节点
     * @param source 插入的节点
     */
    public void pushAfter(DoubleNode<Item> target, DoubleNode<Item> source) {
        if (target.equals(last)) {
            pushLast(source.item);
            return;
        }
        Iterator iterator = iterator();
        while (iterator.hasNext()) {
            DoubleNode<Item> currentNode = (DoubleNode<Item>) iterator.next();
            if (currentNode.equals(target)) {
                DoubleNode<Item> oldNext = currentNode.next;
                currentNode.next = source;
                source.pre = currentNode;
                source.next = oldNext;
                N++;
            }
        }
    }

    /**
     * 删除指定节点（如果有重复节点，只删除第一个）
     *
     * @param target
     * @return 被删除的节点
     */
    public void remove(DoubleNode<Item> target) {
        if (target.equals(first)) {
            pop();
            return;
        }
        if (target.equals(last)) {
            popLast();
            return;
        }
        Iterator iterator = iterator();
        while (iterator.hasNext()) {
            DoubleNode<Item> currentNode = (DoubleNode<Item>) iterator.next();
            if (currentNode.equals(target)) {
                currentNode.pre.next = currentNode.next;
                N--;
            }
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {
        Ex31_DoubleLinkedStack<String> stack = new Ex31_DoubleLinkedStack<>();
        stack.push("aa");
        stack.push("bb");
        stack.push("cc");
        stack.push("dd");
        stack.push("ee");

        // 应该打印：ee dd cc bb aa 00 11 22 33
        stack.pushLast("00");
        stack.pushLast("11");
        stack.pushLast("22");
        stack.pushLast("33");

        // 应该打印：ee dd cc bb aa 00
        StdOut.println("popLast:" + stack.popLast());
        StdOut.println("popLast:" + stack.popLast());
        StdOut.println("popLast:" + stack.popLast());
        StdOut.println("------------");

        DoubleNode<String> newNode = new DoubleNode<>();
        newNode.item = "pushBefore";
        stack.pushBefore(stack.getFirst(), newNode); // 相当于压栈
        stack.pushBefore(stack.getLast(), newNode);  // 在last节点（00）之前添加

        DoubleNode<String> newNode2 = new DoubleNode<>();
        newNode2.item = "pushAfter";
        stack.pushAfter(stack.getLast(), newNode2); // 相当于添加尾节点
        stack.pushAfter(stack.getFirst(), newNode2); // 头节点的后续节点

        stack.remove(newNode2);
        stack.remove(stack.getFirst());
        stack.remove(stack.getLast());

        StdOut.println("first:" + stack.getFirst());
        StdOut.println("last:" + stack.getLast());
        StdOut.println("------------");

        Iterator iterator = stack.iterator();
        while (iterator.hasNext()) {
            DoubleNode<String> next = (DoubleNode<String>) iterator.next();
            StdOut.println(next.item);
        }
    }

    public DoubleNode getFirst() {
        return first;
    }

    public DoubleNode getLast() {
        return last;
    }
}
