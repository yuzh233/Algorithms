package chapter_1._3_bag_queue_stack;

import edu.princeton.cs.algs4.StdOut;

import java.io.File;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/09 18:42
 * 递归打印文件列表
 */
public class Ex43 {
    public static void main(String[] args) {
//        File file = new File(args[0]);
        File file = new File("D:\\IdeaProjects\\Algorithms\\src");
        recursionPrint(file, "|");
    }

    public static void recursionPrint(File file, String str) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                StdOut.println(str + "-- " + f.getName());
            } else if (f.isDirectory()) {
                StdOut.println(str + "—— " + f.getName());
                // 每一次递归，目录就深入一级，用标记隔开。
                recursionPrint(f, "|  " + str);
            }
        }
    }
}
