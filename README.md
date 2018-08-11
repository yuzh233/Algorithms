---
title: 数据结构与算法
date: 2018-08-05 
tags: 数据结构 算法
---

[TOC]

#  第一章 基础
## 1.1 基础编程模型

### 格式化输出

从标准输出流中打印随机生成的数值，“%.2f\n”表示输出两位小数精度的浮点型数值并换行。

cmd运行需要注意的几个地方：

1.我们的工程一般使用utf-8编码，但是windows系统默认gbk编码，所以编译javac会出现“找不到gbk编码的字符映射”。解决办法：编译时指定参数 `-encoding utf-8`

2.“找不到某个类”，程序中引用了非当前目录的jar文件，在本路径编译会找不到jar包，需要执行参数：`-Djava.ext.dirs=jar包作为路径`。

3.“无法运行主类”，检查是否配置了classpath环境变量，`CLASSPATH=".;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar;"`

4.如果被编译类有包，需要在该包下执行编译和运行，最终该类的编译和运行命令：

	javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -encoding utf-8  chapter_1/programming_model/RandomSeq.java
	java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib  chapter_1/programming_model/RandomSeq 10 1 100

```java
public class RandomSeq {
    public static void main(String[] args) {
        // 打印n个(lo,hi)之间的随机值
        int N = Integer.parseInt(args[0]);
        double lo = Double.parseDouble(args[1]);
        double hi = Double.parseDouble(args[2]);
        for (int i = 0; i < N; i++) {
            // 返回随机实数
            double x = StdRandom.uniform(lo, hi);
            StdOut.printf("%.2f\n", x);
        }
    }
}
```
注意：java要求参数的数据类型和转换代码表示的数据类型必须相同，printf()的第一个String字符串也可以包含其他字符。所有非格式的字符串会被传递到输出之中，而格式化的字符串则会被参数的值所代替。例如：
```java
 Std.printf("PI is %.2f\n",Math.PI);
```
会打印出：PI is 3.14

### 标准输入
#### 特点

标准输入流最重要的特点就是这些值会在程序读取之后消失，程序读取了就不能回退再次读取。

#### 重定向与管道

使输出重定向到一个文件中，而不是终端打印：
`java RandomSeq 1000 100.0 200.0 > data.txt`，每次打印都会向文件追加内容。

从文件读取输入流而不是等待用户输入，“<”是一个操作符，它告诉系统从文件中作为输入流而不是等终端用户输入。
`java Average < data.txt`

将一个程序的输出重定向为一个程序的输入叫做`管道`。
`java RandomSeq 1000 100.0 200.0 | Java Average`，该命令将RandomSeq的标准输出和Average的标准输入指定为了同一个流。看起来的效果就是Average运行时从RandomSeq的输出作为了自己的输入。这种写法的好处在于它能够突破输入输出流长度的限制，有效的利用了系统资源。RandomSeq调用了printf()时，向输入流末尾添加了一条字符串；Average调用readDouble()时，就从输入流开头删除了一个字符串。

#### 二分查找
读取终端输入流中的值，如果该值在指定文件中不存在则返回这个值，否则不返回。

```java
public class BinarySearch {
    public static int rank(int key, int[] arr) {
            // 使用lo和hi变量保证key一定在arr[lo...hi]中
            int lo = 0;
            int hi = arr.length - 1;
            for (int i = 0; i < hi; i++) {
                // 取中间值索引，当查找的范围在左边，lo始终为0，当查找的范围在右边，中间值索引就是起始值索引+前后折半的值
                int mid = lo + (hi - lo) / 2;
                // 小于中间值，查找范围缩小到左边
                if (key < arr[i]) {
                    hi = mid - 1;
                }
                // 大于中间值，查找范围缩小到右边
                if (key > arr[i]) {
                    lo = mid + 1;
                } else {
                    return i;
                }
            }
            return -1;
        }
        
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // Reads all integers from a file and returns them as an array of integers. argument：filename
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            // Reads the next token from standard input, parses it as an integer,
            int key = StdIn.readInt();
            if (rank(key, whitelist) < 0) {
                StdOut.println(key);
            }
        }
        long end = System.currentTimeMillis();
        StdOut.println("time->:" + (end - start));
    }
}
```

命令行参数：

    编译忽略过期警告：
    javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -Xlint:deprecation  -encoding utf-8 
        chapter_1/programming_model/BinarySearch.java
    运行：传入一个文件路径，等待用户输入，比较输入的值是否在文件中存在
    java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib  chapter_1/programming_model/BinarySearch 
        D:\IdeaProjects\Algorithms\algs4-data\tinyW.txt
    运行：传入一个文件路径，指定输入流来源于文件，从tinyT.txt中作为输入流，比较tinyT.txt里的每个值是否在tinyW.txt中存在
    java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib  chapter_1/programming_model/BinarySearch 
        D:\IdeaProjects\Algorithms\algs4-data\tinyW.txt < D:\IdeaProjects\Algorithms\algs4-data\tinyT.txt


## 1.2 数据抽象
## 1.3 背包、队列和栈
### 背包（Bag）

是一种不支持从中删除元素的数据类型，其主要目的用来帮助用例（应用程序）收集元素并迭代遍历搜集到的所有元素（检查背包是否为空，或获取背包中元素的数量）。使用背包说明元素的处理是**无序**的。

典型用例：计算标准输入中所有double值的平均值和标准差
```java
public class Stats {
    public static void main(String[] args) {
        Bag<Double> numbers = new Bag<Double>();
        while (!StdIn.isEmpty()) {
            numbers.add(StdIn.readDouble());
        }
        int N = numbers.size();
        double sum = 0.0;
        for (double x : numbers) {
            sum += x;
        }
        double mean = sum / N;

        sum = 0.0;
        for (double x : numbers) {
            sum += (x - mean) * (x - mean);
        }
        double std = Math.sqrt(sum / N - 1);

        StdOut.printf("Mean: %.2f\n", mean);
        StdOut.printf("Std dec: %.2f\n", std);
    }
}
```
### 队列（Queue）
是一种基于先进先出（FIFO）策略的集合类型。队列是许多日常现象的模型，也是无数应用程序的核心。

典型用例：读取文件中的所有数字并放入数组中，使用队列和好处在于用例无需知道文件中的数字的大小即可将文件中的所有数字放入数组中，首先将文件中的所有数字按**顺序**放入队列中，再从队列中按**顺序**一个一个取出放入数组，队列中元素的顺序就是文件中数字的顺序。
```java
public class QueueDemo {
    public static int[] readInts(String name) {
        In in = new In(name);
        Queue<Integer> q = new Queue<>();
        while (!in.isEmpty()) {
            q.enqueue(in.readInt());
        }

        int N = q.size();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = q.dequeue();
        }
        return a;
    }

    public static void main(String[] args) {
        readInts(args[0]);
    }
}
```

### 下压栈（栈、Stack）
是一种基于后进先出（LIFO）策略的集合类型。生活中常见的后进先出策略的例子比如：桌面上放成一叠的邮件，当收信时将邮件压入（push）最顶端，取信时从最顶端将其弹出（pop）。这种策略好处在于我们能够及时的看到最新的邮件，坏处就是当没有清理栈时，某些较早的邮件永远不会被阅读。

典型用例：用元素保存集合的同时颠倒他们的顺序，Reverse会把标准输入中的所有整数逆序排列。
```java
public class Reverse {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        while (!StdIn.isEmpty()) {
            stack.push(StdIn.readInt());
        }

        for (int i : stack) {
            StdOut.println(i);
        }
    }
}
```

### 栈的典型用例：双栈算术表达式求值算法 

编写一个算法来模拟系统运算算术表达式： `( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )` ，输入一个表达式字符串，为了简化问题，我们假设表达式只由运算符+、-、*、/，小括号，和数字组成，并且每个元素之间都用一个空格隔开。 

双栈算术表达式求值算法核心利用了用两个栈：一个保存运算符、一个保存操作数。处理逻辑如下：

* 当遇到操作数将操作数压入操作数栈
* 当遇到运算符将运算符压入运算符栈
* 忽略左括号
* 当遇到右括号时，弹出一个运算符，弹出所需数量的操作数，并将操作数和运算符的运算结果压入操作数栈

```java
public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        while (!StdIn.isEmpty()) {
            // 循环读取每一个操作符，如果是运算符则压入运算符栈
            String s = StdIn.readString();
            if (s.equals("(")) {
            } else if (s.equals("+")) {
                ops.push(s);
            } else if (s.equals("-")) {
                ops.push(s);
            } else if (s.equals("*")) {
                ops.push(s);
            } else if (s.equals("/")) {
                ops.push(s);
            } else if (s.equals("sqrt")) {
                ops.push(s);
            } else if (s.equals(")")) {
                // 如果运算符为 ） ，弹出运算符和操作数，计算结果并压入操作数栈
                String op = ops.pop();
                double v = vals.pop(); // 弹出栈顶的操作数。弹出栈元素是在栈中删除了的
                if (op.equals("+")) {
                    v = vals.pop() + v; // 再弹出一个栈顶操作数，与前面弹出的栈顶操作数（v）做运算并重新赋值给变量v
                } else if (op.equals("-")) {
                    v = vals.pop() - v; // 第二次弹出的操作数在前，第一次弹出的操作数在后。因为先进栈的操作数后弹出，后进栈的元素先弹出
                } else if (op.equals("*")) {
                    v = vals.pop() * v;
                } else if (op.equals("/")) {
                    v = vals.pop() / v;
                } else if (op.equals("sqrt")) {
                    v = Math.sqrt(v);
                }
                vals.push(v); // 运算后的值入栈，进行运算的两个操作数被弹出（删除）了。
            } else {
                // 如果既不是运算符也不是括号，则就是数字，将其压入数值栈。
                vals.push(Double.parseDouble(s));
            }
        }
        StdOut.println(vals.pop());
    }
}
```
求值算法轨迹图：
![Alt text](/alg_img/1.jpg)

### 集合类数据类型的实现

#### 基于顺序存储结构的集合类型实现：
```java
public class ResizingArrayStack<Item> implements Iterable<Item> { 
    private Item[] a; 
    private int N;

    public ResizingArrayStack(int cap) {
        a = (Item[]) new Object[cap];
    }
    
    public void push(Item item) {
        if (N == a.length) {
            resize(a.length * 2);
        }
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) { 
            temp[i] = a[i];
        }
        a = temp;
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayStack<>();
    }
    
    private class ReverseArrayStack<Item> implements Iterator<Item> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i == 0;
        }

        @Override
        public Item next() {
            return (Item) a[--i];
        }
    }
}
```

#### 基于链式存储结构的集合类型实现：
```java
public class LinkedStack<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    private class Node<Item> {
        private Item item;
        private Node next;
    }

    private class listIterator<Item> implements Iterator<Item> {
        private Node currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {
            Item item = (Item) currentNode.item;
            currentNode = currentNode.next;
            return item;
        }
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        Node<Item> oldFirst = first;
        first = first.next;
        N--;
        return oldFirst.item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new listIterator<>();
    }
}
```
```java
public class LinkedQueue<Item> implements Iterable {
    // ......
   public void enqueue(Item item) {
       Node<Item> oldLast = last;
       last = new Node();
       last.item = item;
       if (isEmpty()) {
           first = last;
       } else {
           oldLast.next = last;
       }
       N++;
   }

   public Item dequeue() {
       Node<Item> oldFirst = first;
       first = first.next;
       if (isEmpty()) {
           last = null;
       }
       N--;
       return oldFirst.item;
   }
}
```
更多拓展在练习题：

下压栈：

[补全表达式转为中序表达式](https://github.com/yuzh233/Algorithms/blob/master/src/chapter_1/_3bag_queue_stack/Ex9.java)

[中序表达式转后序表达式](https://github.com/yuzh233/Algorithms/blob/master/src/chapter_1/_3bag_queue_stack/Ex10.java)

[后序表达式求值实现简单计算器](https://github.com/yuzh233/Algorithms/blob/master/src/chapter_1/_3bag_queue_stack/Ex11_EvaluatePostfix.java)

队列：

[读取倒数第K个字符串](https://github.com/yuzh233/Algorithms/blob/master/src/chapter_1/_3bag_queue_stack/Ex15.java)

链表：

[LinkedStackExercise.java](https://github.com/yuzh233/Algorithms/blob/master/src/chapter_1/_3bag_queue_stack/LinkedStackExercise.java)

双向链式存储结构集合数据类型实现：

[Ex31_DoubleLinkedStack.java](https://github.com/yuzh233/Algorithms/blob/master/src/chapter_1/_3bag_queue_stack/Ex31_DoubleLinkedStack.java)

随机背包：

[Ex34_RandomBag.java](https://github.com/yuzh233/Algorithms/blob/master/src/chapter_1/_3bag_queue_stack/Ex34_RandomBag.java)

Josephus生存游戏：

[Ex37_Josephus.java](https://github.com/yuzh233/Algorithms/blob/master/src/chapter_1/_3bag_queue_stack/Ex37_Josephus.java)

可连接队列、栈：

[Ex47.java](https://github.com/yuzh233/Algorithms/blob/master/src/chapter_1/_3bag_queue_stack/Ex47.java)


## 1.4 算法分析
## 1.5 案例研究：union-find算法