package chapter_2._5sorting_application;

import chapter_2._3quicksort.Quick;
import edu.princeton.cs.algs4.StdOut;
import lombok.Data;

import java.io.File;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/29 10:35
 */
@Data
public class Ex28_FileSorter implements Comparable<Ex28_FileSorter> {
    private File file;

    public static void main(String[] args) {
        String path = "D:\\IdeaProjects\\Algorithms\\src\\chapter_2\\_5sorting_application";
        File file = new File(path);
        File[] files = file.listFiles();
        Ex28_FileSorter[] fileSorter = new Ex28_FileSorter[files.length];
        int i = 0;
        for (File f : files) {
            Ex28_FileSorter sorter = new Ex28_FileSorter();
            sorter.setFile(f);
            fileSorter[i++] = sorter;
        }

        new Quick().sort(fileSorter);

        for (Ex28_FileSorter f : fileSorter) {
            StdOut.println(f.getFile());
        }
    }

    @Override
    public int compareTo(Ex28_FileSorter that) {
        String thisName = this.getFile().getName();
        thisName = thisName.substring(0, thisName.lastIndexOf("."));
        String thatName = that.getFile().getName();
        thatName = thatName.substring(0, thatName.lastIndexOf("."));

        char[] thisChars = thisName.toCharArray();
        char[] thatChars = thatName.toCharArray();

        int len = thisChars.length > thatChars.length ? thisChars.length : thatChars.length;
        for (int i = 0; i < len; i++) {
            char a, b;
            try {
                a = thisChars[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                return -1;
            }
            try {
                b = thatChars[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                return 1;
            }

            if (a > b) return 1;
            if (a < b) return -1;
        }
        return 0;
    }
}
