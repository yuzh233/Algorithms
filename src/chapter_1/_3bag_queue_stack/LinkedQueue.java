package chapter_1._3bag_queue_stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/06 14:50
 * 基于链表的队列
 */
public class LinkedQueue<Item> implements Iterable {
    private Node first; // 最早添加的节点
    private Node last; // 最近添加的节点
    private int N;

    private class Node<Item> {
        private Item item;
        private Node next;
    }

    /**
     * Ex47 连接两个队列
     *
     * @param queue2
     */
    public void catenation(LinkedQueue<Item> queue2) {
        last.next = queue2.first;
        last = queue2.last;
    }

    /**
     * 入列：在表尾插入节点
     */
    public void enqueue(Item item) {
        Node<Item> oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        N++;
    }

    /**
     * 出列：在表头删除节点
     *
     * @return
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Node<Item> oldFirst = first;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return oldFirst.item;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Node<Item> oldFirst = first;
        return oldFirst.item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    @Override
    public Iterator iterator() {
        return new listIterator();
    }

    private class listIterator<Item> implements Iterator<Item> {
        private Node currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {
            Item item = (Item) currentNode.item;
            currentNode = currentNode.next;
            return item;
        }
    }

    public static void main(String[] args) {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("aaa");
        queue.dequeue();
//        queue.dequeue();
        queue.enqueue("bbb");
        queue.enqueue("ccc");
        queue.enqueue("ddd");

        Iterator iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
