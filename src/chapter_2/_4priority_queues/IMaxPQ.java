package chapter_2._4priority_queues;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/24 13:41
 * <p>
 * 优先队列应用程序编程接口
 */
public interface IMaxPQ<Key extends Comparable<Key>> {

    /**
     * 向优先队列插入一个元素
     */
    void insert(Key v);

    /**
     * 返回最大元素
     */
    Key max();

    /**
     * 删除最大元素
     */
    Key delMax();

    boolean isEmpty();

    int size();
}
