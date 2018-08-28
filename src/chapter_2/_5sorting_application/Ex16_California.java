package chapter_2._5sorting_application;

import chapter_2._3quicksort.Quick;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import lombok.Data;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/28 15:03
 */
@Data
public class Ex16_California implements Comparable<Ex16_California> {
    private static final String ORDER = "WRQOJMVAHBSGZXNTCIEKUPDYFL";
    private String who;

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Ex16_California[] a = new Ex16_California[N];
        int i = 0;
        while (!StdIn.isEmpty()) {
            String who = StdIn.readLine();
            Ex16_California california = new Ex16_California();
            california.setWho(who);
            a[i++] = california;
        }

        new Quick().sort(a);

        for (int j = 0; j < a.length; j++) {
            StdOut.println(a[j]);
        }
    }

    @Override
    public int compareTo(Ex16_California o) {
        char thiz = this.getWho().charAt(0);
        char that = o.getWho().charAt(0);
        int a = ORDER.indexOf(thiz);
        int b = ORDER.indexOf(that);
        if (a > b) return 1;
        if (a < b) return -1;
        return 0;
    }
}


// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation -Xlint:unchecked -encoding utf-8  chapter_2\_5sorting_application\Ex16_California.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_2/_5sorting_application/Ex16_California 8 < D:/IdeaProjects/Algorithms/src/chapter_2/_5sorting_application/16.txt
