package chapter_1._5_union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/13 15:24
 */
public class Ex1 implements UF {
    private int[] id;
    private int count;

    Ex1(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int i = 0;
        if (find(p) == find(q)) {
            i += 2;
            return;
        }
        // 将p所属分量的值改为q所属分量的值
        StdOut.println("before-> id[" + p + "]=" + id[p] + ", id[" + q + "]=" + id[q]);
        for (i = 0; i < id.length; i++) {
            if (id[i] == id[p]) {
                id[i] = id[q];
            }
        }
        StdOut.println("after-> id[" + p + "]=" + id[p] + ", id[" + q + "]=" + id[q]);
        StdOut.println("access array count -> " + i);
        StdOut.println("---------------");
        count--;
    }

    @Override
    public int find(int p) {
        return id[p];
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
        Ex1 unionFind = new Ex1(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            unionFind.union(p, q);
        }
        StdOut.println(unionFind.count + " components");
    }

}

// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation  -Xlint:unchecked -encoding utf-8  chapter_1\_5_union_find\Ex1.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_1/_5_union_find/Ex1 < D:/IdeaProjects/Algorithms/src/chapter_1/_5_union_find/Ex1.txt