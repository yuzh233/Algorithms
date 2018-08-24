package chapter_2._1elementary_sorts;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/24 10:59
 * <p>
 * 不交换元素的插入排序：用一个变量存储目标元素，将目标元素与前驱元素比较。
 * - 外循环遍历每一个需要插入的元素（目标元素）
 * - 内循环将目标元素与左边元素（当前元素）比较，不执行交换。
 */
public class Ex25 extends Example {

    @Override
    public void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            Comparable target = a[i]; // 保存目标元素的值
            int j; // 保存目标元素应该插入的位置
            for (j = i; j > 0 && less(target, a[j - 1]); j--) {
                a[j] = a[j - 1]; // 前驱元素后移，j--，当前元素变成了j - 1，如果j-1-1 <= target 的话，j-1就是应该插入的位置。
            }
            a[j] = target; // 这里的a[j]是内循环中j--之后的a[j-1]，因为前驱元素后移了，所以在当前位置插入。
        }
    }

    public static void main(String[] args) {
        Comparable[] a = new Comparable[]{2, 0, 5, 7, 9, 8, 3, 1, 4, 6};
        new Ex25().sort(a);
        assert isSorted(a);
        show(a);
    }
}
