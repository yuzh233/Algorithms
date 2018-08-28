package chapter_2._5sorting_application;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import lombok.Data;
import org.junit.Test;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/28 10:26
 * <p>
 * 逆域名排序
 */
@Data
public class Ex14_Domain implements Comparable<Ex14_Domain> {
    private String[] domainName;

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Ex14_Domain[] domainNames = new Ex14_Domain[N]; // 所有域名列表
        int i = 0;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readLine();
            s = s.toLowerCase();
            String[] name = s.split("\\.");
            reverseDomainName(name);
            Ex14_Domain domain = new Ex14_Domain();
            domain.setDomainName(name);
            domainNames[i++] = domain;
        }

        /***********************************************************
         * 域名逆序排序 注意：字符顺序在前的ASCII值 < 字母顺序在前的ASCII值
         * 希望 com.baidu在 edu.princeton.cs.algs4之前显示，需要降序排序。
         ***********************************************************/
        QuickSortDesc.sort(domainNames);
        show(domainNames);
    }

    /**
     * 域名反转
     *
     * @param name 正序的域名
     * @return 逆序的域名
     */
    public static void reverseDomainName(String[] name) {
        int N = name.length;
        int mid = N / 2 - 1;
        for (int i = 0; i <= mid; i++) {
            String tem = name[i];
            name[i] = name[N - 1 - i];
            name[N - 1 - i] = tem;
        }
    }

    @Test
    public void test() {
        Ex14_Domain a = new Ex14_Domain();
        a.setDomainName(new String[]{"com", "baidu"});
        Ex14_Domain b = new Ex14_Domain();
//        b.setDomainName(new String[]{"com", "baidu", "news"});
        b.setDomainName(new String[]{"edu", "princeton", "cs", "algs4"});
        int r = a.compareTo(b);
        System.out.println(r);
    }

    @Override
    public int compareTo(Ex14_Domain o) {
        String[] a = this.getDomainName(); // test: com.baidu
        String[] b = o.getDomainName(); // test: com.baidu.news
        int len = a.length > b.length ? a.length : b.length;
        for (int i = 0; i < len; i++) { // 遍历每个域名段
            String ai;
            String bi;
            try {
                ai = a[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                return 1;
            }
            try {
                bi = b[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                return -1;
            }
            if (ai.equals(bi)) continue;

            // 域名段拆分为字符段
            char[] acs = ai.toCharArray();
            char[] bcs = bi.toCharArray();
            int len2 = acs.length > bcs.length ? acs.length : bcs.length;
            for (int j = 0; j < len2; j++) {
                char a1;
                char a2;
                try {
                    a1 = acs[j];
                } catch (ArrayIndexOutOfBoundsException e) {
                    return 1;
                }
                try {
                    a2 = bcs[j];
                } catch (ArrayIndexOutOfBoundsException e) {
                    return -1;
                }
                if (a1 < a2) return 1;
                if (a1 > a2) return -1; // A-Z:65-90 a-z:97-122
            }
        }
        return 0;
    }

    public static void show(Ex14_Domain[] domainNames) {
        for (int i = 0; i < domainNames.length; i++) {
            String[] name = domainNames[i].getDomainName();
            for (int j = 0; j < name.length; j++) {
                StdOut.print(name[j]);
                if (j != name.length - 1) StdOut.print(".");
            }
            StdOut.println();
        }
    }
}

// javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation -Xlint:unchecked -encoding utf-8  chapter_2\_5sorting_application\Ex14_Domain.java
// java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_2/_5sorting_application/Ex14_Domain 8 < D:/IdeaProjects/Algorithms/src/chapter_2/_5sorting_application/14.txt
