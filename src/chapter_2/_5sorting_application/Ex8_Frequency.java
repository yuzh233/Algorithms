package chapter_2._5sorting_application;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import lombok.Data;

import java.util.Comparator;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/27 13:35
 */
@Data
public class Ex8_Frequency {
    private String str;
    private int cnt;

    Ex8_Frequency() {

    }

    Ex8_Frequency(String str, int cnt) {
        this.str = str;
        this.cnt = cnt;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Ex8_Frequency[] fres = new Ex8_Frequency[N];
        int i = 0;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            int cnt = 1;
            boolean exit = false; // 是否有重复元素
            for (int k = 0; k < i; k++) {
                if (fres[k].str.equals(s)) {
                    fres[k].cnt += 1;
                    exit = true;
                }
            }
            if (!exit) {
                Ex8_Frequency frequency = new Ex8_Frequency(s, cnt);
                fres[i++] = frequency;
            }
        }
        Ex8_Frequency[] tem = new Ex8_Frequency[i]; // 对这个数组进行排序
        for (int j = 0; j < N && fres[j] != null; j++) {
            tem[j] = fres[j];
        }

        sort(tem);

        for (int j = 0; j < tem.length; j++) {
            StdOut.println(tem[j]);
        }
    }

    private static void sort(Object[] a) {
        int N = a.length;
        CompareFrequency cf = new CompareFrequency();
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && !less(cf, a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    private static boolean less(Comparator c, Object o, Object v) {
        return c.compare(o, v) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    // 比较器
    private static class CompareFrequency implements Comparator<Ex8_Frequency> {
        @Override
        public int compare(Ex8_Frequency o1, Ex8_Frequency o2) {
            if (o1.cnt > o2.cnt) return 1;
            if (o1.cnt < o2.cnt) return -1;
            return 0;
        }
    }
}

// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation -Xlint:unchecked -encoding utf-8  chapter_2\_5sorting_application\Ex8_Frequency.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_2/_5sorting_application/Ex8_Frequency 10 < D:/IdeaProjects/Algorithms/src/chapter_2/_5sorting_application/str.txt
