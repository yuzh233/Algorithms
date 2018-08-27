package chapter_2._5sorting_application;

import chapter_2._4priority_queues.MaxPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import lombok.Data;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/27 22:20 - 0:54
 * <p>
 * 负载均衡问题：假设有M个相同的处理器和N个任务，目标是用尽可能短的时间在这些处理器上完成所有任务。一种较优调度方法是 `最大优先`，将任务按耗时
 * 降序排列，为每个任务依次分配给当前可用的处理器。维护一个优先队列存储所有处理器，队列中元素的优先级是处理器上运行任务的耗时之和，每一次都删除
 * 优先级最低的处理器（处理掉它的任务），将下一个任务分配给该处理器在重新加入队列。
 * <p>
 * 用例：接收整数M作为命令行参数，从输入流中读取任务的名称和所需时间，用负载均衡策略的最长时间优先原则打印一份调度计划，将所有任务分配给M个处理器
 * 并使得完成所有任务所需总时间最少。
 * <p>
 * 备注：命令行编译时带参数化数据类型通不过，Ex13_LPT2是固定数据的演示。
 */
@Data
public class Ex13_LPT {
    private static int M = 0; // 处理器数量
    private static MaxPQ<CPU> pq = null; // 负载均衡服务器的优先队列（模拟调度）
    private static Task[] tasks = null; // 所有任务
    private static CPU[] cpus; // 所有CPU

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        M = Integer.parseInt(args[1]);
        pq = new MaxPQ<>(M); // 处理器数量
        initCPU(M);// 实例化所有CPU
        tasks = new Task[N]; // 命令行输入任务个数
        int i = 0;
        while (!StdIn.isEmpty() && i < N) {
            String s = StdIn.readLine();
            String[] strings = s.split("\\s+");
            Task task = new Task(strings[0], Float.parseFloat(strings[1]));
            tasks[i++] = task;
        }
        QuickSortDesc.sort(tasks);

        /***********************************************************
         * 实现负载均衡
         ***********************************************************/
        for (int k = 0; k < tasks.length; k++) {
            addTaskToCPU(tasks[i]);
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
     * 初始化CPU
     */
    private static void initCPU(int m) {
        cpus = new CPU[m];
        for (int i = 0; i < m; i++) {
            cpus[i] = new CPU();
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


// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation -Xlint:unchecked -encoding utf-8  chapter_2\_5sorting_application\Ex13_LPT.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_2/_5sorting_application/Ex13_LPT 4 10 < D:/IdeaProjects/Algorithms/src/chapter_2/_5sorting_application/13.txt
