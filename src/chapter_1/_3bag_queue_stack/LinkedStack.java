package chapter_1._3bag_queue_stack;

import edu.princeton.cs.algs4.StdOut;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/06 14:25
 * 基于链表实现的栈
 */
public class LinkedStack<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    /**
     * 链表节点对象
     *
     * @param <Item>
     */
    private class Node<Item> {
        private Item item;
        private Node next;
    }

    /**
     * Ex47 连接两个栈
     *
     * @param stack2
     */
    public void catenation(LinkedStack<Item> stack2) {
        Node<Item> lastNode = null;
        Node<Item> currentNode = first;
        while (!isEmpty()) {
            if (currentNode.next == null) {
                lastNode = currentNode;
                break;
            }
            currentNode = currentNode.next;
        }
        lastNode.next = stack2.first;
    }

    /**
     * Ex12 接收一个字符串栈作为参数，返回该栈的副本
     *
     * @param targetStack
     * @return
     */
    private static LinkedStack<String> copy(LinkedStack<String> targetStack) {
        // 把目标栈逐个弹出压入新栈后顺序倒置了，如果是双向队列可以从尾遍历到头压入新栈。
        LinkedStack<String> tem = new LinkedStack<>();
        LinkedStack<String> newStack = new LinkedStack<>();
        for (String s : targetStack) {
            tem.push(s);
        }
        for (String s : tem) {
            newStack.push(s);
        }
        return newStack;
    }

    /**
     * 迭代器（所有在迭代中对值的修改都是不允许的！）
     *
     * @param <Item>
     */
    private class listIterator<Item> implements Iterator<Item> {
        private Node currentNode = first;
        private int count = size();

        @Override
        public boolean hasNext() {
            if (size() != count) {
                throw new ConcurrentModificationException("不可修改值！");
            }
            return currentNode != null;
        }

        @Override
        public Item next() {
            if (size() != count) {
                throw new ConcurrentModificationException("不可修改值！");
            }
            Item item = (Item) currentNode.item;
            currentNode = currentNode.next;
            return item;
        }
    }

    /**
     * 压栈：从表头插入节点
     * <p>
     * 1.暂存旧头节点
     * 2.创建新头节点
     * 3.新头节点下一个节点指向旧头节点
     *
     * @param item
     */
    public void push(Item item) {
        Node oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    /**
     * 出栈：从表头删除节点
     * <p>
     * 1.暂存旧头节点（被删除的）
     * 2.旧头节点的下一个节点的引用赋值给头节点
     * 3.弹出旧头节点
     *
     * @return
     */
    public Item pop() {
        Node<Item> oldFirst = first;
        first = oldFirst.next;
        N--;
        return oldFirst.item;
    }

    /**
     * 返回最近添加的元素而不弹出
     *
     * @return
     */
    public Item peek() {
        return (Item) first.item;
    }


    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new listIterator<>();
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("aa");
        stack.push("bb");
        stack.push("cc");
        stack.push("dd");
        stack.push("ee");
        stack.push("ff");
        stack.push("gg");
        stack.pop();
        stack.pop();

        Iterator<String> iterator = stack.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if ("dd".equals(s)) {
                stack.pop();
            }
            System.out.println(s);
        }
        LinkedStack<String> newStack = LinkedStack.copy(stack);
        StdOut.println(newStack == stack);

        Iterator<String> iterator2 = newStack.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
//        System.out.println(stack.isEmpty());
//        System.out.println(stack.size());
    }
}
