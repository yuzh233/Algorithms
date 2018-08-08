package chapter_1.bag_queue_stack;

import java.util.Iterator;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/06 15:48
 * 基于链表结构的背包实现
 */
public class LinkedBag<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    private class Node<Item> {
        private Item item;
        private Node next;
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

    /**
     * 背包虽然说数据是无需的，实际上访问的顺序也是后进先出，和栈一样。
     *
     * @param item
     */
    public void add(Item item) {
        Node oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public int size() {
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new listIterator<>();
    }

    public static void main(String[] args) {
        LinkedBag<String> bag = new LinkedBag<>();
        bag.add("aa");
        bag.add("bb");
        bag.add("cc");
        Iterator<String> iterator = bag.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}