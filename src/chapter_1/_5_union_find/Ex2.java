package chapter_1._5_union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/13 15:24
 */
public class Ex2 implements UF {
    private int[] id;
    private int count;

    Ex2(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        StdOut.println("before-> id[" + p + "]=" + id[p] + ", id[" + q + "]=" + id[q]);
        int i = 2;
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            StdOut.println("access array count -> " + i);
            return;
        }
        id[pRoot] = qRoot;
        StdOut.println("after-> id[" + p + "]=" + id[p] + ", id[" + q + "]=" + id[q]);
        StdOut.println("access array count -> " + (i + 1));
        StdOut.println("---------------");
        count--;
    }

    @Override
    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    @Override
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Ex2 unionFind = new Ex2(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            unionFind.union(p, q);
        }
        StdOut.println(unionFind.count + " components");
    }

}

// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation  -Xlint:unchecked -encoding utf-8  chapter_1\_5_union_find\Ex2.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_1/_5_union_find/Ex2 < D:/IdeaProjects/Algorithms/src/chapter_1/_5_union_find/Ex1.txt