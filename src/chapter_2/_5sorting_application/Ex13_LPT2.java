package chapter_2._5sorting_application;

import chapter_2._4priority_queues.MaxPQ;
import edu.princeton.cs.algs4.StdOut;
import lombok.Data;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/27 22:20
 */
@Data
public class Ex13_LPT2 {
    private static int M = 0;
    private static MaxPQ<CPU> pq = null;
    private static Task[] tasks = null;
    private static CPU[] cpus;

    static {
        CPU cpu0 = new CPU();
        CPU cpu1 = new CPU();
        CPU cpu2 = new CPU();
        CPU cpu3 = new CPU();
        cpus = new CPU[]{cpu0, cpu1, cpu2, cpu3};
    }

    public static void main(String[] args) {
        pq = new MaxPQ<CPU>(4);
        int i = 0;
        Task task1 = new Task("task1", 3600);
        Task task2 = new Task("task2", 1800);
        Task task3 = new Task("task3", 900);
        Task task4 = new Task("task4", 7200);
        Task task5 = new Task("task5", 30);
        Task task6 = new Task("task6", 1000);
        Task task7 = new Task("task7", 3200);
        Task task8 = new Task("task8", 7500);
        Task task9 = new Task("task9", 8100);
        Task task10 = new Task("task10", 2000);
        tasks = new Task[]{task1, task2, task3, task4, task5, task6, task7, task8, task9, task10};
        QuickSortDesc.sort(tasks);
//        QuickSortDesc.show(tasks);

        // 实现负载均衡
        for (int k = 0; k < tasks.length; k++) {
            addTaskToCPU(tasks[k]);
        }

        /**
         * 有两种情况需要将队列剩余所有任务处理掉
         * 1. 当元素少于队列大小，即所需处理的任务少于队列的大小。（因为这里的算法是队列满了再处理优先级最大的任务，当任务较少队列永远不会满）
         * 2. 元素已全部加入任务后。（元素全部加入了队列就不会添加新的元素，不添加新元素队列就不会检测CPU是否有空闲，不检测就不会执行任务）
         */
        while (!pq.isEmpty()) {
            StdOut.println("last process task is: " + pq.delMax().task);
        }
    }

    /**
     * 添加一个任务到CPU
     * - 如果当前四个CPU有空闲的（CPU里面的Task为null），放入一个任务到CPU中并将CPU加入到队列。
     * - 如果没有空闲的CPU，删除优先级最低的任务CPU，重新加入任务。
     */
    public static void addTaskToCPU(Task task) {
        CPU cpu = getIdleCPU();
        if (cpu == null) { // 如果没有空闲的CPU，调用proccess删除一个任务。
            process();
            cpu = getIdleCPU();
        }
        cpu.setTask(task);
        pq.insert(cpu);
    }

    /**
     * 处理优先级最低的任务（CPU运行时间最长优先级最低）
     */
    public static void process() {
        CPU cpu = pq.max();
        StdOut.println("process task is: " + cpu.task);
        cpu.setTask(null);
        pq.delMax();
    }

    /**
     * 返回空闲的CPU
     */
    public static CPU getIdleCPU() {
        for (int i = 0; i < 4; i++) {
            if (cpus[i].task == null)
                return cpus[i];
        }
        return null;
    }

    @Data
    private static class CPU implements Comparable<CPU> {
        private Task task;

        @Override
        public int compareTo(CPU o) {
            return this.task.compareTo(o.task);
        }
    }
}
