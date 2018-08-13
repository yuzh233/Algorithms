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
