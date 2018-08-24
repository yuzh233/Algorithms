package chapter_2._4priority_queues;

import edu.princeton.cs.algs4.StdOut;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/24 16:19
 * <p>
 * 基于有序链表栈的优先队列
 * 方式一：修改pop()找到并返回最大元素（实现）
 * 方式二：修改push()使得元素逆序存放，最后一个进栈的元素最大。
 */
public class MaxPQ4Linked<Key extends Comparable<Key>> implements MaxPQ<Key>, Iterable<Key> {
    private Node<Key> first;
    private int N;

    private class Node<Key> {
        private Key key;
        private Node<Key> next;
    }

    @Override
    public void insert(Key v) {
        Node oldFirst = first;
        first = new Node<>();
        first.key = v;
        first.next = oldFirst;
        N++;
    }

    @Override
    public Key max() {
        Iterator<Key> iterator = iterator();
        int i = 0;
        Key max = null;
        while (iterator.hasNext()) {
            Key next = iterator.next();
            if (i == 0) max = next;
            if (i > 0 && next.compareTo(max) > 0) {
                max = next;
            }
            i++;
        }
        return max;
    }

    @Override
    public Key delMax() {
        Iterator<Key> iterator = iterator();
        int i = 0;
        Key max = null;
        int maxIndex = i;
        while (iterator.hasNext()) {
            Key next = iterator.next();
            if (i == 0) max = next;
            if (i > 0 && next.compareTo(max) > 0) {
                max = next;
                maxIndex = i;
            }
            i++;
        }
        // 删除指定节点
        delete(maxIndex);
        return max;
    }

    private boolean delete(int k) {
        if (k >= size() || k < 0) {
            return false;
        }
        Node<Key> preNode = null;
        Node currentNode = first;
        for (int i = 0; i < size(); i++) {
            if (i == k) {
                break;
            }
            preNode = currentNode;
            currentNode = currentNode.next;
        }
        if (preNode == null) {
            // 如果前节点为空，表明当前是头节点
            Node<Key> oldFirst = first;
            first = oldFirst.next;
        } else {
            preNode.next = currentNode.next;
        }
        N--;
        return true;
    }

    @Override
    public Iterator<Key> iterator() {
        return new MaxPQ4Linked.listIterator();
    }

    private class listIterator implements Iterator<Key> {
        private Node<Key> currentNode = first;
        private int count = size();

        @Override
        public boolean hasNext() {
            if (size() != count) {
                throw new ConcurrentModificationException("不可修改值！");
            }
            return currentNode != null;
        }

        @Override
        public Key next() {
            if (size() != count) {
                throw new ConcurrentModificationException("不可修改值！");
            }
            Key key = currentNode.key;
            currentNode = currentNode.next;
            return key;
        }
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return N;
    }

    public void show() {
        Iterator<Key> iterator = iterator();
        while (iterator.hasNext()) {
            Key key = iterator.next();
            StdOut.print(key + " ");
        }
    }

    public static void main(String[] args) {
        MaxPQ4Linked<Integer> pq = new MaxPQ4Linked<>();
        pq.insert(4);
        pq.insert(1);
        pq.insert(8);
        pq.insert(7);
        pq.insert(9);
        pq.insert(0);
        pq.insert(6);
        pq.insert(5);
        pq.insert(3);
        pq.insert(2);

        pq.delMax();
        pq.delMax();
        StdOut.println(pq.max());

        pq.show();
    }
}
