package chapter_1._5_union_find;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/12 16:41
 * <p>
 * union-find算法API
 */
public interface UF {

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
    void union(int p, int q);

    /**
     * p所在分量（相等整数对）的标识符
     */
    int find(int p);

    /**
     * p和q存在同一分量返回true
     */
    boolean connected(int p, int q);

    /**
     * 分量的个数
     */
    int count();
}
