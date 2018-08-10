package chapter_1._3_bag_queue_stack;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/09 10:27
 * <p>
 * 随机背包
 * 要求迭代时随机访问所有元素（那么添加有序迭代无序）
 */
public class Ex34_RandomBag<Item> implements Iterable<Item> {
    private Item[] items;
    private int N;


    Ex34_RandomBag() {
        items = (Item[]) new Object[5];
    }

    public void add(Item item) {
        if (N == items.length) {
            resize(N * 2);
        }
        items[N++] = item;
    }

    public void resize(int max) {
        Item[] newItems = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Random random = new Random(System.currentTimeMillis());
        private ArrayList<Integer> tem = new ArrayList<>();
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i != N;
        }

        @Override
        public Item next() {
            int randomIndex = random.nextInt(N); // [0,N)
            while (tem.contains(randomIndex)) {
                randomIndex = random.nextInt(N);
            }
            Item item = (Item) items[randomIndex];
            tem.add(randomIndex);
            i++;
            return item;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public static void main(String[] args) {
        Ex34_RandomBag<String> randomBag = new Ex34_RandomBag<>();
        randomBag.add("lisa");
        randomBag.add("rose");
        randomBag.add("jack");
        randomBag.add("john");
        randomBag.add("tom");

        Iterator<String> iterator = randomBag.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            StdOut.println(next);
        }

    }
}
