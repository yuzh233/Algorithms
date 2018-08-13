package chapter_1._5_union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/12 17:05
 * <p>
 * union-find的第一种实现：quick-find算法
 */
public class QuickFind implements UF {
    private int[] id; // 连通分量标识符集合
    private int count; // 连通分量数量

    /**
     * 初始化所有连通分量
     */
    QuickFind(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) {
            // 已经在同一个分量中不做处理
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
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
        QuickFind quickFind = new QuickFind(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (quickFind.connected(p, q)) {
                continue;
            }
            quickFind.union(p, q);
            StdOut.println("union:" + p + " " + q);
        }
        StdOut.println(quickFind.count + " components");
    }
}

// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation  -Xlint:unchecked -encoding utf-8  chapter_1\_5_union_find\QuickFind.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_1/_5_union_find/QuickFind < D:/IdeaProjects/Algorithms/algs4-data/tinyUF.txt
