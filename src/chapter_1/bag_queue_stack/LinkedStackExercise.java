package chapter_1.bag_queue_stack;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.Objects;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/07 20:30
 * 基于链表的练习，操作的是节点，pop返回的是节点而不是item域。
 */
public class LinkedStackExercise<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int N;

    public static class Node<Item> {
        private Item item;
        private Node next;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(item, node.item) &&
                    Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(item, next);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    '}';
        }

        public Item getItem() {
            return item;
        }

        public Node getNext() {
            return next;
        }
    }

    /**
     * Ex19 删除尾节点
     * <p>
     * 1.用一个变量保存当前节点的上一个节点（因为该实现不是双向链表的）
     * 2.将上一个节点赋值给尾节点
     *
     * @return
     */
    public boolean deleteLast() {
        Node<Item> preNode = null;
        Node currentNode = first;
        for (int i = 0; i < size(); i++) {
            if (i == size() - 2) {
                preNode = currentNode;
            }
            currentNode = currentNode.next;
        }
        last = preNode;
        last.next = null;
        N--;
        return true;
    }

    /**
     * Ex20 删除指定索引位置的节点
     * <p>
     * 1.用一个变量保存当前节点的上一个节点（因为该实现不是双向链表的）
     * 2.上一个节点的下一个节点设置成当前节点的下一个节点
     *
     * @param k 被删除的元素索引
     * @return
     */
    public boolean delete(int k) {
        if (k >= size() || k < 0) {
            return false;
        }
        Node<Item> preNode = null;
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
            Node<Item> oldFirst = first;
            first = oldFirst.next;
        } else {
            preNode.next = currentNode.next;
        }
        N--;
        return true;
    }

    /**
     * Ex21 查找节点：如果指定节点的item域=key，返回true，否则false。
     *
     * @param target
     * @param key
     * @return
     */
    public static boolean find(LinkedStackExercise target, String key) {
        Iterator iterator = target.iterator();
        while (iterator.hasNext()) {
            Node node = (Node) iterator.next();
            String next = (String) node.item;
            if (next.trim().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ex24 删除指定节点的后续节点（若参数节点或参数节点的后续节点不存在不做操作）
     *
     * @param targetNode
     * @return
     */
    public boolean removeAfter(Node targetNode) {
        if (targetNode.equals(last)) {
            return false;
        }
        Iterator iterator = iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Node node = (Node) iterator.next();
            if (targetNode.equals(node)) {
                StdOut.println(node);
                last = node;
                last.next = null;
                N = i + 1;
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Ex25 接收两个链表节点作为参数，将第二个节点插入链表使之成为第一个节点的后序节点（若两个参数都为空不操作）
     *
     * @param target 链表中已经存在的节点
     * @param node   要插入的节点
     * @return
     */
    public boolean insertAfter(Node target, Node node) {
        if (target == null || node == null) {
            return false;
        }
        Iterator iterator = iterator();
        while (iterator.hasNext()) {
            Node next = (Node) iterator.next();
            if (next.equals(target)) {
                Node oldNext = next.next;
                next.next = node;
                node.next = oldNext;
                N++;
                return true;
            }
        }
        return false;
    }

    /**
     * Ex26 接收一个链表和一个字符串Key参数，删除链表中所有item域为key的节点。（未完成）
     * <p>
     * 调用Ex20的实现（删除指定节点），每次循环删除一个节点之后跳出重新开始循环。
     *
     * @param stack
     * @param key
     * @return
     */
    public static void remove(LinkedStackExercise stack, String key) {
        Iterator iterator = stack.iterator();

        int i = 0;
        while (iterator.hasNext()) {
            Node node = (Node) iterator.next();
            if (node.item.equals(key)) {
                stack.delete(i);
            }
            i++;
        }
    }

    /**
     * Ex27 返回key最大的节点的值
     *
     * @param first
     * @return
     */
    public static int max(Node first) {
        if (first == null) {
            return 0;
        }
        int max = (int) first.item;
        Node next = first.next;
        while (next != null) {
            int num = (int) next.item;
            if (max < num) {
                max = num;
            }
            next = next.next;
        }
        return max;
    }

    /**
     * Ex28 基于Ex27的递归实现
     *
     * @param target 要进行比较的第一个值
     */
    public static int rMax(Node target) {
        if (target == null) {
            return 0;
        }
        if (target.next == null) {
            return (int) target.item;
        }
        int before = (int) target.item;
        int after = (int) target.next.item;
        if (before > after) {
            // 前面的大于后面的，把前面的赋值给后面的
            target.next.item = before;
            return rMax(target.next);
        } else {
            return rMax(target.next);
        }
    }

    @Override
    public Iterator iterator() {
        return new listIterator();
    }

    /**
     * 迭代器遍历的是节点，不是item域的数据。
     */
    private class listIterator implements Iterator {
        private Node currentNode = first;
        private int i = N;

        @Override
        public boolean hasNext() {
            return i != 0;
        }

        @Override
        public Node next() {
            Node node = currentNode;
            currentNode = currentNode.next;
            i--;
            return node;
        }
    }

    public void push(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        if (first.next == null) {
            last = first;
        }
        N++;
    }

    public Node pop() {
        Node<Item> oldFirst = first;
        first = oldFirst.next;
        N--;
        return oldFirst;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return first == null;
    }


    public static void main(String[] args) {
        LinkedStackExercise<Integer> stackExercise = new LinkedStackExercise<>();
        stackExercise.push(123);
        stackExercise.push(55);
        stackExercise.push(44);
        stackExercise.push(334);
        stackExercise.push(22);
       /* LinkedStackExercise<String> stackExercise2 = new LinkedStackExercise<>();
        stackExercise2.push("aa");
        stackExercise2.push("aa");
        stackExercise2.push("cc");
        stackExercise2.push("dd");
        stackExercise2.push("aa");
        LinkedStackExercise.remove(stackExercise2, "aa");*/

//        StdOut.println(stackExercise.deleteLast());
//        StdOut.println(stackExercise.deleteLast());
//        StdOut.println(stackExercise.delete(0));


        StdOut.println(rMax(stackExercise.getFirst()));

       /* Iterator<String> iterator = stackExercise.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }*/

       /* StdOut.println("---------");
        Iterator<String> iterator2 = stackExercise2.iterator();
//        StdOut.println(find(stackExercise, "aa"));
        while (iterator2.hasNext()) {
            StdOut.println(iterator2.next());
        }*/
    }


    public Node<Item> getFirst() {
        return first;
    }

    public Node<Item> getLast() {
        return last;
    }
}
