package chapter_1._5_union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/13 0:02
 */
public class QuickUnion implements UF {
    private int[] id; // 树的每一个节点（触点）
    private int count; // 连通分量（根节点）的数量

    QuickUnion(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        // 获取两个节点所属分量（根节点）
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        // 把p的根节点的爸爸设为q的根节点，这样p和q就有了共同的爸爸。
        id[pRoot] = qRoot;
        count--;
    }

    @Override
    public int find(int p) {
        // 当id[p]的值是本身，说明它是根节点（分量名）
        while (p != id[p]) {
            p = id[p];
        }
        return p; // 所在分量就是根节点
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
        QuickUnion quickUnion = new QuickUnion(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (quickUnion.connected(p, q)) {
                continue;
            }
            quickUnion.union(p, q);
            StdOut.println("union:" + p + " " + q);
        }
        StdOut.println(quickUnion.count + " components");
    }
}

// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation  -Xlint:unchecked -encoding utf-8  chapter_1\_5_union_find\QuickUnion.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_1/_5_union_find/QuickUnion < D:/IdeaProjects/Algorithms/algs4-data/mediumUF.txt