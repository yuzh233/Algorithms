package chapter_2._5sorting_application;

import chapter_2._4priority_queues.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import lombok.Data;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/27 19:30
 */
public class Ex12_SPT {

    public static void main(String[] args) {
        MinPQ<Task> taskPQ = new MinPQ<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readLine();
            String[] strings = s.split("\\s+");
            Task task = new Task(strings[0], Float.parseFloat(strings[1]));
            taskPQ.insert(task);
        }

        // task plan
        while (!taskPQ.isEmpty()) {
            StdOut.println(taskPQ.delMin());
        }
    }

    @Data
    private static class Task implements Comparable<Task> {
        private String application;
        private float time;

        public Task() {

        }

        public Task(String app, float time) {
            this.application = app;
            this.time = time;
        }

        @Override
        public int compareTo(Task o) {
            if (this.time > o.time) return 1;
            if (this.time < o.time) return -1;
            return 0;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "application='" + application + '\'' +
                    ", time=" + time +
                    '}';
        }
    }
}


// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation -Xlint:unchecked -encoding utf-8  chapter_2\_5sorting_application\Ex12_SPT.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_2/_5sorting_application/Ex12_SPT < D:/IdeaProjects/Algorithms/src/chapter_2/_5sorting_application/12.txt
