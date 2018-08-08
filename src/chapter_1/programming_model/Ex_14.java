package chapter_1.programming_model;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/04 15:51
 * <p>
 * 复习对数：
 * 如果a^x=N (a>0 && a≠1)，那么称 x = log以a为底N的对数
 */
public class Ex_14 {
    public static void main(String[] args) {
        float result = lg(32);
        float result2 = lg2(32);
        System.out.println(result + " = " + result2);
    }

    /**
     * 接收一个整数N，返回不大于 log以2为底N的对数（log2N） 的最大整数（小于等于x的最大整数）
     *
     * @param N
     * @return 不是求对数的值x，而是求小于对数结果的最大整数
     */
    public static float lg(int N) {
        if (N == 0) {
            // N=0无意义
            return -1;
        }
        if (N == 1) {
            // N=1,对数结果=0
            return 0;
        }
        // 2^x=N 用2乘x的结果与N做比较，如果大于>N，x-1；如果小于N，x+1
        for (int x = 2; ; ) { // 2的几次幂
            // 开平方
            float re = 1; //保存结果
            for (int n = 1; n <= x; n++) {
                re *= 2;
            }
            if (re < N) {
                x++;
            } else if (re == N) {
                return x;
            } else {
                return x - 1;
            }
        }
    }

    // 网上大神的
    private static int lg2(int n) {
        int shiftRightCount = 0;
        do {
            n >>= 1;
            shiftRightCount++;
        } while (n != 0);
        return shiftRightCount - 1;
    }
}
