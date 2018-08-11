package chapter_1._4_analysis_of_algorithms;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/10 19:32
 * <p>
 * 表示计时器的抽象数据类型
 */
public class StopWatch {
    private final long start;

    public StopWatch() {
        start = System.currentTimeMillis();
    }

    /**
     * 返回对象创建以来所经过的时间
     *
     * @return
     */
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
}
