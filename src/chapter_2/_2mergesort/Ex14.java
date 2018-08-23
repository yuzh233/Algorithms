//package chapter_2._2mergesort;
//
//import chapter_1._3bag_queue_stack.LinkedQueue;
//import edu.princeton.cs.algs4.StdOut;
//
//import java.util.Iterator;
//
///**
// * @Author: yu_zh
// * @DateTime: 2018/08/22 10:08
// * <p>
// * 归并有序的队列
// */
//public class Ex14 {
//
//    /**
//     * 归并两个有序队列到第一个队列中
//     */
//    public LinkedQueue<Comparable> merge(LinkedQueue q1, LinkedQueue q2) {
//        LinkedQueue<Comparable> re = new LinkedQueue<>();
//        Iterator i1 = q1.iterator();
//        Iterator i2 = q2.iterator();
//        Comparable s1 = null;
//        Comparable s2 = null;
//        Comparable max;
//        if (i1.hasNext()) s1 = (Comparable) i1.next();
//        if (i2.hasNext()) s2 = (Comparable) i2.next();
//        while (true) {
//            if (s1.compareTo(s2) <= 0) {
//                max = s2;
//                re.enqueue(s1);
//                q1.dequeue();
//                if (i1.hasNext()) s1 = (Comparable) i1.next();
//            } else {
//                max = s1;
//                re.enqueue(s2);
//                q2.dequeue();
//                if (i2.hasNext()) s2 = (Comparable) i2.next();
//            }
//            if (i2.hasNext() == false) {
//                mergeLastQueue(max, re, i1);
//                break;
//            }
//            if (i1.hasNext() == false) {
//                mergeLastQueue(max, re, i2);
//                break;
//            }
//        }
//        return re;
//    }
//
//    /**
//     * 当某个队列合并完之后将另一个队列全部入列到待合并的的大队列
//     */
//    private void mergeLastQueue(Comparable max, LinkedQueue re, Iterator it) {
//        re.enqueue(max);
//        while (it.hasNext()) {
//            Comparable s2 = (Comparable) it.next();
//            re.enqueue(s2);
//        }
//    }
//
//    public static void main(String[] args) {
//        LinkedQueue<Comparable> q1 = new LinkedQueue<>();
//        LinkedQueue<Comparable> q2 = new LinkedQueue<>();
//        q1.enqueue(3);
//        q1.enqueue(6);
////        q1.enqueue(8);
////        q1.enqueue(16);
//
//        q2.enqueue(2);
//        q2.enqueue(8);
////        q2.enqueue(4);
////        q2.enqueue(6);
////        q2.enqueue(7);
////        q2.enqueue(9);
////        q2.enqueue(13);
////        q2.enqueue(15);
//
//        /*for (int i = 0; i < 10000; i++) {
//            if (i % 2 == 0) {
//                q1.enqueue(i);
//            } else {
//                q2.enqueue(i);
//            }
//        }*/
//
//        Ex14 ex14 = new Ex14();
//        LinkedQueue<Comparable> merge = ex14.merge(q1, q2);
//        Iterator iterator = merge.iterator();
//        while (iterator.hasNext()) {
//            StdOut.print(iterator.next() + " ");
//        }
//    }
//}
