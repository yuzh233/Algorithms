package chapter_1._3_bag_queue_stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/05 23:05
 * 可扩容的参数化类型栈的实现
 */
public class ResizingArrayStack<Item> implements Iterable<Item> { // 实现该接口返回一个迭代器对象
    private Item[] a; // 栈内部维护一个数组
    private int N; // 栈的容量

    public ResizingArrayStack(int cap) {
        a = (Item[]) new Object[cap];
    }

    /**
     * 压栈：压栈之前先判断是否需要扩容
     *
     * @param item
     */
    public void push(Item item) {
        if (N == a.length) {
            resize(a.length * 2);
        }
        a[N++] = item;
    }

    /**
     * 出栈：出栈之前判断是否需要缩容
     * 如果栈容量小于数组容量的四分之一，表明数组容量缩减一半之后的容量还是栈容量的两倍多，可以缩容。
     *
     * @return
     */
    public Item pop() {
        // 弹出栈顶元素，实际上被弹出的元素引用仍然存在于数组中，只是在这个栈中表示已经“不存在”了。这时仍然存在的元素称为游离对象，
        // 要避免对象游离，做法就是将该引用置为null
        Item item = a[--N];
        a[N] = null; // 避免对象游离
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /**
     * 栈扩容
     * 创建一个新的数组，把旧的数组存进去
     * 始终保持数组的容量为栈容量的两倍
     *
     * @param max 扩容后的容量
     */
    public void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) { // 注意：循环条件不能是数组容量，而是标识栈的实例变量N。
            temp[i] = a[i];
        }
        a = temp;
    }

    /**
     * 返回一个迭代器对象
     *
     * @return
     */
    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayStack<>();
    }

    /**
     * 迭代操作
     *
     * @param <Item>
     */
    private class ReverseArrayStack<Item> implements Iterator<Item> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i == 0;
        }

        @Override
        public Item next() {
            return (Item) a[--i];
        }
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> s = new ResizingArrayStack<>(100);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.push(item);
            } else if (!s.isEmpty()) {
                StdOut.print(s.pop() + " ");
            }
        }
        StdOut.print("(" + s.size() + " left on stack)");
    }
}


//javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:unchecked  -encoding utf-8  chapter_1/_3_bag_queue_stack/ResizingArrayStack.java
//java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_1/_3_bag_queue_stack/ResizingArrayStack < D:\IdeaProjects\Algorithms\algs4-data\tobe.txt