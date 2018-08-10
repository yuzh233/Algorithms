package chapter_1._3_bag_queue_stack;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/06 14:53
 * 对于节点的操作API
 */
public interface Node<Item> {

    /**
     * 在表头添加节点
     */
    void addHead();

    /**
     * 在表头删除（弹出）节点
     *
     * @return
     */
    Item removeHead();

    /**
     * 在表尾添加节点
     */
    void addLast();


    /**
     * 删除指定节点
     *
     * @param item
     * @return
     */
    Item removeNode(Item item);

    /**
     * 在指定节点前插入节点
     *
     * @param target 指定的节点
     * @param item   新插入的节点
     */
    void addPreNode(Item target, Item item);

    /**
     * 遍历节点
     *
     * @return
     */
    Item iterator();
}
