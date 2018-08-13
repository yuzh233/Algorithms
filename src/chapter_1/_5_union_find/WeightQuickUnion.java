package chapter_1._5_union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/13 13:53
 * <p>
 * 加权QuickUnion算法
 */
public class WeightQuickUnion implements UF {
    private int[] id; // 每个触点的值是父链接
    private int count; // 连通分量个数
    private int[] sz; // 各个根节点对应的分量大小（节点个数）

    WeightQuickUnion(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
        // 初始化每个根节点对应分量的大小都是1
        sz = new int[N];
        for (int i = 0; i < count; i++) {
            sz[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        // 不再是把p的根节点的爸爸设为q的根节点了，而是比较p的分量个数和q的分量个数，分量个数小的认分量个数大的当爸爸
        if (sz[pRoot] > sz[qRoot]) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            // 当p分量大小 <= q分量大小时，默认q的根节点认q的根节点当爸爸
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
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
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        WeightQuickUnion union = new WeightQuickUnion(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (union.connected(p, q)) {
                continue;
            }
            union.union(p, q);
            StdOut.println("union:" + p + " " + q);
        }
        StdOut.println(union.count + " components");
    }
}


// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation  -Xlint:unchecked -encoding utf-8  chapter_1\_5_union_find\WeightQuickUnion.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_1/_5_union_find/WeightQuickUnion < D:/IdeaProjects/Algorithms/algs4-data/largeUF.txt