package chapter_2._5sorting_application;

import lombok.Data;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/27 22:08
 */
@Data
public class Task implements Comparable<Task> {
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
