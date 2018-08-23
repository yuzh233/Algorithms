//package chapter_2._2mergesort;
//
//import chapter_1._3bag_queue_stack.LinkedQueue;
//import edu.princeton.cs.algs4.StdOut;
//
//import java.util.Iterator;
//
///**
// * @Author: yu_zh
// * @DateTime: 2018/08/22 15:06
// * <p>
// * 自底向上实现队列的归并排序
// */
//public class Ex15 {
//    LinkedQueue<LinkedQueue> aux = new LinkedQueue();
//
//    public void sort(LinkedQueue q) {
//        Iterator<LinkedQueue> iterator = q.iterator();
//        while (iterator.hasNext()) {
//            aux.enqueue(iterator.next());
//        }
//        while (aux.size() != 1) {
//            LinkedQueue q1 = aux.dequeue();
//            LinkedQueue q2 = aux.dequeue();
//            LinkedQueue<Comparable> mergeQ = new Ex14().merge(q1, q2);
//            aux.enqueue(mergeQ);
//        }
//        Iterator<LinkedQueue> iterator2 = aux.iterator();
//        while (iterator2.hasNext()) {
//            StdOut.println(iterator2.next().dequeue());
//        }
//    }
//
//    public static void main(String[] args) {
//        LinkedQueue queue = new LinkedQueue();
//        LinkedQueue q1 = new LinkedQueue();
//        q1.enqueue(3);
//        LinkedQueue q2 = new LinkedQueue();
//        q2.enqueue(6);
//        LinkedQueue q3 = new LinkedQueue();
//        q3.enqueue(2);
//        LinkedQueue q4 = new LinkedQueue();
//        q4.enqueue(8);
//        LinkedQueue q5 = new LinkedQueue();
//        q5.enqueue(5);
//        LinkedQueue q6 = new LinkedQueue();
//        q6.enqueue(13);
//        LinkedQueue q7 = new LinkedQueue();
//        q7.enqueue(23);
//        LinkedQueue q8 = new LinkedQueue();
//        q8.enqueue(10);
//        LinkedQueue q9 = new LinkedQueue();
//        q9.enqueue(0);
//        LinkedQueue q10 = new LinkedQueue();
//        q10.enqueue(9);
//        LinkedQueue q11 = new LinkedQueue();
//        q11.enqueue(4);
//        LinkedQueue q12 = new LinkedQueue();
//        q12.enqueue(7);
//
//        queue.enqueue(q1);
//        queue.enqueue(q2);
//        queue.enqueue(q3);
//        queue.enqueue(q4);
//        queue.enqueue(q5);
//        queue.enqueue(q6);
//        queue.enqueue(q7);
//        queue.enqueue(q8);
//        queue.enqueue(q9);
//        queue.enqueue(q10);
//        queue.enqueue(q11);
//        queue.enqueue(q12);
//
//        /*for (int i = 0; i < 10; i++) {
//            LinkedQueue<Comparable> q = new LinkedQueue<>();
//            q.enqueue(StdRandom.uniform(10));
//            queue.enqueue(q);
//        }*/
//        new Ex15().sort(queue);
//    }
//}
