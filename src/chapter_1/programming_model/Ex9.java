package chapter_1.programming_model;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/04 14:23
 * <p>
 * 将一个正整数用二进制表示并转换成Sting类型的值
 * 十进制转二进制：除2取余，直到除完，余数从尾到头拼接的数字就是二进制数。
 */
public class Ex9 {
    public static void main(String[] args) {
        String s = toBinaryString(320);
        StdOut.println(s);
//        StdOut.println(Integer.toBinaryString(500));
    }

    public static String toBinaryString(int N) {
        String s = "";
        for (int n = N; n > 0 ; n /= 2){
            s = (n % 2) + s;
        }
        return s;
    }
}
