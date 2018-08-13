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

    /**
     * 归并分量
     * <p>
     * 如果不想连（调用connected()返回false），则将p、q相连：把p所在分量的值全部改为q所在分量的值（也可以把q的改成p的）比如:
     * <p>
     * 触点 2 6 4 属于同一分量，它们的分量值是相同的 id[2] == id[6] == id[4] == 2
     * 触点 3 8 5 属于同一分量，它们的分量值是相同的 id[3] == id[8] == id[5] == 3
     * <p>
     * 这时输入中传入两个参数 p = 6 ，q = 5 ，两个触点属于不同的分量，把其中一个触点的分量改为另外一个触点的分量，即：
     * id[2] == id[6] == id[4] == id[3] == id[8] == id[5] == 3 ，此时两个分量归并后只剩下一个连通分量。
     */
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
