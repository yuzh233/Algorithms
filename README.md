---
title: 数据结构与算法
date: 2018-08-05 
tags: 数据结构 算法
---

#  第一章 基础
## 1.1 基础编程模型

### 格式化输出

从标准输出流中打印随机生成的数值，“%.2f\n”表示输出两位小数精度的浮点型数值并换行。

cmd运行需要注意的几个地方：

1. 我们的工程一般使用utf-8编码，但是windows系统默认gbk编码，所以编译javac会出现“找不到gbk编码的字符映射”。解决办法：编译时指定参数 `-encoding utf-8`

2. “找不到某个类”，程序中引用了非当前目录的jar文件，在本路径编译会找不到jar包，需要执行参数：`-Djava.ext.dirs=jar包作为路径`。

3. “无法运行主类”，检查是否配置了classpath环境变量，`CLASSPATH=".;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar;"`

4. 如果被编译类有包，需要在该包下执行编译和运行，最终该类的编译和运行命令：

	javac -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib -encoding utf-8 chapter_1/programming_model/RandomSeq.java
	java -Djava.ext.dirs=D:\IdeaProjects\Algorithms\lib chapter_1/programming_model/RandomSeq 10 1 100

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
## 1.5 案例研究：并查集（union-find）算法
### 问题由来 —— 动态连通性
#### 问题：
> 程序从输入中每次读取一对整数 P 和 Q ，如果已知的所有整数对不能证明他们是“相连”的，那么把他们“连起来”，并打印；如果能证明他们是相连的则不处理，继续读取下一对整数对。当两个对象（整数点）相连时称为属于一个*等价类*。

#### 概念：
如果两个对象“相连”是一种等价关系，那么它具有以下特性：

* 自反性：P和P是相连的（就是一个点和自己本身是相连的，em...）；
* 对称性：若P和Q是相连的，那么Q和P也是相连的；
* 传递性：若P和Q相连且Q和R相连，那么P和R是相连的

#### 理解并查集
以上问题其实就是并查集的一个具体案例，关于并查集，解释如下：
> 并查集，在一些有N个元素的集合应用问题中，我们通常是在开始时让每个元素构成一个单元素的集合，然后按一定顺序将属于同一组的元素所在的集合合并，其间要反复查找一个元素在哪个集合中。

“并查集”是一种`不相交集合`的数据类型，初始时并查集中的元素是不相交的，经过一系列的基本操作(Union)，最终合并成一个大的集合。

#### API

* 初始化触点
* 连接触点
* 某个触点所在的连通分量
* 判断两个触点是否在同一个连通分量之中
* 返回连通分量的数量

```java
public interface UF {
    /**
     * 连接P和Q
     */
    void union(int p, int q);

    /**
     * p所在分量（相等整数对）的标识符
     */
    int find(int p);

    /**
     * p和q存在同一分量返回true
     */
    boolean connected(int p, int q);

    /**
     * 分量的个数
     */
    int count();
}
```

### 实现一：quick-find 算法
#### 数据结构：数组

1. 用数组 `id[]` 表示每一个触点的值，数组索引表示触点，触点的值就是分量的值，触点值相同表明分量相同。
2. 初始化时每个触点的值都是该触点的索引。
   
   * 比如：触点0 = id[0] = 0; 触点1 = id[1] = 1; 触点3 = id[3] = 3;

3. 多个触点属于同一个连通分量时，其中某个触点的值“代表”该连通分量的值，把其他触点的值统一成所属分量的代表值，比如：

   * 要把`id[4] (值为4)` 和 `id[8] (值为8)` 相连为同一分量，可以把id[4]的值改成id[8]的值，那么把 id[8] 值称为该连通分量的`代表（或标识符或值）`；也可以把id[8]的值改成id[4]的值，连通分量的值就是id[4]了。
  
   * 要把`id[5] (值为5)` 和 `id[4] (假设所属分量值为8)` 相连为同一分量，连通分量的值以id[5]为代表，那么所有值为 8 的分量的值都改成了 5。

4. 连通分量中的所有触点的值是统一的。

#### 算法分析
可以快速进行 find 操作，即可以快速判断两个节点是否连通。

同一连通分量的所有节点的 id 值相等。

但是 union 操作代价却很高，需要将其中一个连通分量中的所有节点 id 值都修改为另一个节点的 id 值。

```java
public class QuickFind implements UF {
    private int[] id; // 连通分量标识符集合
    private int count; // 连通分量数量

    /**
     * 初始化所有连通分量
     */
    QuickFind(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }
    
    @Override
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) {
            // 已经在同一个分量中不做处理
            return;
        }

        for (int i = 0; i < count; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
            count--;
        }
    }

    @Override
    public int find(int p) {
        return id[p];
    }

    @Override
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    @Override
    public int count() {
        return count;
    }
}
```

### 实现二：quick-union 算法
#### 数据结构：树

1. 同样以 `id[]` 表示每一个节点（触点）的值，但节点的值不是分量的值，每一个节点值是以其父节点的`索引号`的id[]值，该节点最终的值是根节点的值（父节点指向父节点，直到指向根节点）。
   * 比如`节点4（id[4]=9）` 是 `节点8` 的父节点，那么节点8`id[8]`的值就是id[4]，即：id[8] = id[4] = 9

2. 初始化时每个触点的值都是该触点的索引，并且都是根节点。

3. 属于同一个连通分量的所有节点都属于同一颗数，判断是否属于同一分量需要判断是否属于同一棵树，我们可以把根节点代表分量的标识符。

4. 两个节点的联合操作，操作的是两个节点的根节点，只需要将一个根节点的父节点设为另外一个根节点，这样两个节点（分量）联合成了一个节点（分量）。

  * 比如：初始化时，每个节点都是根节点。 id[0] = 0; id[1] = 1; id[3] = 3; id[7] = 7 ...
  * 联合节点 0 和 1，将id[0]的父节点设为id[1]，即：id[0] = id[1] = 1
  * 联合节点 3 和 0，将id[3]的父节点设为id[0]，即：id[3] = id[0] = id[1] = 1
  * 联合节点 1 和 7，将id[1]的父节点设为id[7]，即：id[1] = id [7] = 7（此时id[3] = id[0] = id[1] = id [7] = 7）

#### 算法分析
可以快速进行 union 操作，只需要修改一个节点的 id 值即可。

union操作，固定的将左边的树链接到右边的树，导致树的深度很深，进行find()操作时效率变低。

但是 find 操作开销很大，因为同一个连通分量的节点 id 值不同，id 值只是用来指向另一个节点。因此需要一直向上查找操作，直到找到最上层的节点。

这种方法可以快速进行 union 操作，但是 find 操作和树高成正比，最坏的情况下树的高度为触点的数目。

```java
public class QuickUnion implements UF {
    private int[] id; // 树的每一个节点（触点）
    private int count; // 连通分量（根节点）的数量

    QuickUnion(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        // 获取两个节点所属分量（根节点）
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        // 把p的根节点的爸爸设为q的根节点，这样p和q就有了共同的爸爸。
        id[pRoot] = qRoot;
        count--;
    }

    @Override
    public int find(int p) {
        // 当id[p]的值是本身，说明它是根节点（分量名）；若不是，向上循环找到根节点。
        while (p != id[p]) {
            p = id[p];
        }
        return p; // 所在分量就是根节点
    }

    @Override
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    @Override
    public int count() {
        return count;
    }
}
```
#### 轨迹图：
![Alt text](/alg_img/2.jpg)

### 实现三：加权 quick-union 算法
#### 算法分析
加权 quick-union 算法的出现是为了解决 quick-union 中find()操作随着树的深度加深成本变得越来越昂贵的问题。

不再固定的将左边的树链接到右边的树，而是根据树的深度（节点的个数）决定将深度小的树链接在深度大的树，由此降低find()操作次数。

#### 数据结构
1. 和 quick-union 结构相同，仅仅添加了一个用于记录每个分量个数的数组

2. 不再是把p的根节点的爸爸设为q的根节点了，而是比较p的分量个数和q的分量个数，分量个数小的认分量个数大的当爸爸

```java
public class WeightQuickUnion implements UF {
    private int[] id; // 每个触点的值是父链接
    private int count; // 连通分量个数
    private int[] sz; // 各个根节点对应的分量大小（节点个数）

    WeightQuickUnion(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
        // 初始化每个根节点对应分量的大小都是1
        sz = new int[N];
        for (int i = 0; i < count; i++) {
            sz[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        // 不再是把p的根节点的爸爸设为q的根节点了，而是比较p的分量个数和q的分量个数，分量个数小的认分量个数大的当爸爸
        if (sz[pRoot] > sz[qRoot]) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            // 当p分量大小 <= q分量大小时，默认q的根节点认q的根节点当爸爸
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        count--;
    }

    @Override
    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }
}
```
#### 轨迹图：
![Alt text](/alg_img/3.jpg)

### 实现四：路径压缩的加权 quick-union 算法
#### 算法分析
路径压缩的加权 quick-union 算法是为了优化find()操作，减少查找父节点的次数，从而提升查找的效率；

使用路径压缩的加权 quick-union 算法是解决动态连通性问题的最优解；

它将每一个子节点都挂在根节点形成一个近似扁平的树状结构；

每次查找指定节点的根元素（分量）时，都将路径上（该节点的所有父节点）遇到所有节点挂在根节点之下；

压缩加权后的算法find()效率与 quick-find 的效率非常接近。

```java
public class WeightQuickUnion implements UF {
    public int pathCompressionFind(int p) {
        // 先向上循环找到根节点
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }
        // 再次循环，如果当前节点不是根节点，把当前节点挂在根节点上成为根节点的一级节点。
        while (p != id[p]) {
            int tem = p; 
            p = id[p]; 
            id[tem] = root; 
        }
        return root;
    }
    
    public void union(int p, int q) {
        int pRoot = pathCompressionFind(p);
        int qRoot = pathCompressionFind(q);
        if (pRoot == qRoot) {
            return; 
        }
        // 不再是把p的根节点的爸爸设为q的根节点了，而是比较p的分量个数和q的分量个数，分量个数小的认分量个数大的当爸爸
        if (sz[pRoot] > sz[qRoot]) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot]; 
        } else {
            // 当p分量大小 <= q分量大小时，默认q的根节点认q的根节点当爸爸
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot]; 
        }
        count--; 
    }
}
```

# 第二章 排序
待排序的元素需要实现 Java 的 Comparable 接口，该接口有 compareTo() 方法，可以用它来判断两个元素的大小关系。

定义算法模板类API
```java
public abstract class Example {
    
    /**
     * 具体排序算法实现
     */
    public abstract void sort(Comparable[] a);

    /**
     * 对元素进行比较
     * @return first < second ? true : false
     */
    public static boolean less(Comparable first, Comparable second) {
        return first.compareTo(second) < 0;
    }

    /**
     * 把两个元素交换位置
     */
    public static void exch(Comparable[] a, int i, int j) {
        Comparable tem = a[i];
        a[i] = a[j];
        a[j] = tem;
    }
    
    /**
     * 返回序列是否有序（asc）
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                // 后面的元素 < 前面的元素 不是升序排列 返回false
                return false;
            }
        }
        return true;
    }
    
    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
}
```
## 选择排序
首先在数组中找到最小的元素，将其和第一个元素交换；然后继续在第一个元素之后的元素中寻找最小元素，将其和第二个元素交换... 循环往复直到将整个数组排序。

- 外循环遍历数组的每个当前元素
- 内循环遍历当前元素之后的所有元素寻找最小值

### 算法分析
优点：数据移动次数最少，选择排序的交换次数和数组长度N成`线性关系`，其他排序算法不具备该特征。

缺点：运行时间与输入（整个序列的值）无关，一个值相同的或有序的序列和一个随机无序的序列进行排序的时间一样长。
```java
public class Selection extends Example {
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int minIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[minIndex])) {
                    minIndex = j; // 如果后续元素小于最小元素，把后续元素索引赋给最小元素索引。
                }
                exch(a, i, minIndex); // 交换原最小元素与新最小元素位置
            }
        }
    }
    public static void main(String[] args) {
        String[] a = new In().readAllStrings();
        new Selection().sort(a);
        assert isSorted(a); // 验证：确认排序后的算法是有序的，当序列元素相同时无法通过验证。
        show(a);
    }
}
```

## 插入排序
将当前元素插入到当前元素之前的合适位置

首先从数组的第二个元素（目标元素）开始，当目标元素小于前面的元素，交换两者位置（否则不变）；然后目标元素变为第三个元素，将其与第二个元素比较，若小则交换位置（此时目标元素索引为1），再将其与第一个元素对比，循环往复...

- 外循环遍历每一个需要插入的目标元素
- 内循环将目标元素与其左边的每一个元素对比、交换位置，直至目标元素被插入到了合适的位置。
- 目标元素（a[i]）从左到右移动时，其左侧的元素始终时有序的，当其移动到了最右边，数组也完成了排序。

### 算法分析
插入排序所需的时间取决于数组中元素的初始位置。因为当元素有序时不会进行交换，对于一个元素很大的且元素有序（或接近有序）的序列进行排序会比随机顺序的序列进行排序要快得多。
```java
public class Insertion extends Example {
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            // 将当前元素 a[i] 与 其左边的所有元素对比、交换位置
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                // 后面的元素比前面的元素小才进行排序
                exch(a, j, j - 1);
            }
        }
    }
}
```
大幅度提高插入排序的速度，只需要在内循环中将较大的元素向右移动而不总是交换两个元素（这样访问数组的次数会减半），实现见 [练习](https://github.com/yuzh233/Algorithms/blob/master/src/chapter_2/_1elementary_sorts/Ex25.java)。
```java
public class Ex25 extends Example {
    public void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            Comparable target = a[i]; // 保存目标元素的值
            int j; // 保存目标元素应该插入的位置
            for (j = i; j > 0 && less(target, a[j - 1]); j--) {
                a[j] = a[j - 1]; // 前驱元素后移
            }
            a[j] = target;
        }
    }
}
```

## 选择排序与插入排序比较
从直观上来说：
- 选择排序不会访问索引左侧的元素（每次都是从目标元素的索引右边遍历所有元素取最小值进而与目标元素交换位置）
- 插入排序不会访问索引右侧的元素（每次都是目标元素与其左边的每一个元素做对比进而交换位置）

首先规定输入模型：数组中的元素随机排序，且主键值不重复。

速度对比：

- 1000条数据排序100次，选择排序花费0.4s，插入排序花费0.1s；
- 10000条数据排序100次，选择排序花费43.6s，插入排序花费10.2s；

结论：

对于随机排序的无重复主键，插入排序和选择排序的运行时间都是平方级别的。

```java
public class SortCompare {
    public static double time(String alg, Comparable[] a) {
        StopWatch watch = new StopWatch();
        if (alg.equals("Insertion")) {
            new Insertion().sort(a);
        }
        if (alg.equals("Selection")) {
            new Selection().sort(a);
        }
        return watch.elapsedTime();
    }
    
    //使用alg算法将长度为N的数组排序T次
    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N]; // 目标数组
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform(); // 生成随机值
            }
            total += time(alg, a); // 计算T次时间总和
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T); // 算法1的总时间
        double t2 = timeRandomInput(alg2, N, T); // 算法2的总时间
        StdOut.printf("the %s algorithm takes %.1f seconds.\n", alg2, t2); 
        StdOut.printf("the %s algorithm takes %.1f seconds.\n", alg1, t1); 
    }
}
```
## 希尔排序
希尔排序是插入排序的增强版，是为了改进插入排序对于处理大规模乱序数组排序速度过慢的问题。实质上是`分组插入排序`，该方法又称`缩小增量排序`。

> 该方法的基本思想是：先将整个待排元素序列分割成若干个子序列（由相隔某个“增量”的元素组成的）分别进行直接插入排序，然后依次缩减增量再进行排序，待整个序列中的元素基本有序（增量足够小）时，再对全体元素进行一次直接插入排序。因为直接插入排序在元素基本有序的情况下（接近最好情况），效率是很高的，因此希尔排序在时间效率上比前两种方法有较大提高。

序列分组轨迹图：

参考：https://blog.csdn.net/IWantToHitRen/article/details/51583047

对于希尔排序和插入排序速度的对比：
- 10000条数据排序100次，插入排序用时12.3s，希尔排序用时0.3s!；
- 50000条数据排序100次，插入排序用时380.7s，希尔排序用时1.8s!；

以下是结合网上对希尔排序的理解实现的算法以及《算法 第四版》原文中的算法。
```java
public class Shell extends Example {

    /**
     * 根据网上总结自己实现的算法
     */
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int gap = N / 2; gap > 0; gap /= 2) {  // gap：增量（步数），每次循环增量减少一倍，直至增量为1（此时对全部元素进行插入排序）完成排序。
            for (int i = 0; i < gap; i++) { // 把整体序列分为若干子序列。a[i]是每一组的第一个元素
                for (int j = i + gap; j < N; j += gap) { // 每间隔一个增量，获得一个该组的元素。
                    int tarIndex = j; // 目标元素索引，当前元素索引。
                    for (int k = tarIndex; k > i && less(a[k], a[k - gap]); k -= gap) { // 对子序列进行插入排序，将该元素与本组左边所有元素进行比较。
                        exch(a, k, k - gap);
                    }
                }
            }
        }
    }
    
    /**
     * 原文的算法，增量使用了递增序列，有时间再来理解。
     */
    public void sort3(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            // 子数组插入排序
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
```
## 归并排序
归并排序的核心是归并操作，归并排序每次将数组 `递归的` 拆分成两半分别排序，再将两半的结果 `合并` 起来最终实现整个数组的排序。

### 归并操作
归并操作的前提是数组的两边是分别 `有序` 的，将一个“两边有序的数组”合并成一个“整体有序的数组”。
```java
public class Merge extends Example {
    private static Comparable aux[]; // 辅助数组，用于合并操作。
    
    //TODO sort()
    
    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int le = lo;
        int ri = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (le > mid) {
                a[k] = aux[ri++]; // 左边元素用尽，将右边元素一个一个放入a[]
            } else if (ri > hi) {
                a[k] = aux[le++]; // 右边元素用尽，将左边元素一个一个放入a[]
            } else if (less(aux[ri], aux[le])) {
                a[k] = aux[ri++]; // 右边元素小于左边元素，右边元素放入a[]，右边元素索引+1
            } else {
                a[k] = aux[le++]; // 左边元素小于右边元素，左边元素放入a[]，左边元素索引+1
            }
        }
    }
}
```
### 自顶向下归并排序
归并排序不断（递归）的将大数组插拆分为两半，直到不可再拆（小数组仅剩“左右”两个元素），再将两边的数组合并成一个整体有序的大数组。
```java
public class Merge extends Example {
    private static Comparable aux[]; // 辅助数组，用于合并操作。
    
    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid); // 左半边排序
        sort(a, mid + 1, hi); // 右半边排序
        merge(a, aux, lo, mid, hi); // 合并子数组
    }
}
```
每一个节点都是 merge() 操作形成的子数组。当数组不可再分（递归到了最小情况），merge()通过交换前后元素实现排序。

![Alt text](/alg_img/4.jpg)
#### 改进
[对于小规模数组使用插入排序](https://github.com/yuzh233/Algorithms/blob/master/src/chapter_2/_2mergesort/Ex11.java)

[测试数组是否有序](https://github.com/yuzh233/Algorithms/blob/master/src/chapter_2/_2mergesort/Ex11.java)

有参考价值博客：[图解排序算法(四)之归并排序](http://www.cnblogs.com/chengxiao/p/6194356.html)

### 自底向上归并排序
先归并微型数组，再成对归并得到的子数组，直到归并形成一个大数组，排序结束。

- 第一轮：将大数组的每一个元素当作一个子数组（最小的子数组），`两两归并` 子数组（将大小为1的子数组归并成大小为2的子数组）。

- 第二轮：一轮归并之后每个子数组存在 2 个元素，再 `四四归并` 子数组（将大小为2的子数组归并为大小为4的子数组）。

- 第三轮：二轮归并之后每个子数组存在 4 个元素，再 `八八归并` 子数组（将大小为4的子数组归并为大小为8的子数组）。

- ...... 如此往复，直到子数组大小 >= 待排序的大数组，完成了排序。
```java
public class MergeBU extends Example {
    private static Comparable aux[];

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) { // 控制子数组大小呈倍数递增
            /**
             * 遍历每个子数组
             *  lo                                  每个子数组的第一个元素
             *  lo < N - sz                         控制最后一个子数组的开头
             *  lo = lo + sz + sz                   跳到下一个子数组开头
             *  Math.min(lo + sz + sz - 1, N - 1)   最后一个子数组的大小有可能不是sz的整数倍，lo + sz + sz - 1可能会出现数组越界。
             */
            for (int lo = 0; lo < N - sz; lo = lo + sz + sz) {
                int mid = lo + sz - 1;
                int hi = Math.min(lo + sz + sz - 1, N - 1);
                merge(a, aux, lo, mid, hi);
            }
        }
        assert isSorted(a);
    }
}
```
可视图：
![Alt text](/alg_img/5.jpg)

## 快速排序
快速排序同归并排序一样是一种分治的排序算法。它通过 `切分` 递归的将数组分为两个部分，对两个部分分别排序，并保证左边的元素都 <= `切点`，右边的元素都 >= `切点`。  

每次切分都能保证子数组左边的元素小于切点，右边的元素大于切点。通过递归，对左半边和右半边数组再次切分，最终达到整个数组的有序。

切分算法：

- 切点定为子数组的第一个元素（可以是任意元素）

- 指针 i 从左往右扫描 `大于` 切点的值，找到即退出扫描；指针 j 从右往左扫描 `小于` 切点的值，找到即退出扫描。

- 交换 a[i] 与 a[j] 的位置，小的值放在左边，大的值放在右边。

- 若 i 扫描完毕找不到最大值，说明 切点 就是最大值；若 j 扫描完毕找不到最小值，说明 切点 就是最小值

- 为什么指针相遇`(i >= j)`切分结束？因为相遇了代表所有元素都已遍历完毕。

- 指针相遇（双向扫描完毕）之后，交换 切点(v) 与 a[j] 的值，此时a[j]是最后一个小于 v 的值，而切点到了数组中间，最后返回切点索引。

博客参考，对于理解很有帮助：[坐在马桶上看算法：快速排序](http://developer.51cto.com/art/201403/430986.htm)
```java
public class Quick extends Example {
    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi); // 切分
        sort(a, lo, j - 1); // 左半边排序
        sort(a, j + 1, hi); // 右半边排序
    }

    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1; // 左右扫描的指针
        Comparable v = a[lo]; // 切分的元素
        while (true) {
            while (less(a[++i], v)) { // 指针 i 从左往右扫描大于v的值
                if (i == hi) break;
            }
            while (less(v, a[--j])) { // 指针 j 从右往左扫描小于v的值
                if (j == lo) break;
            }
            if (i >= j) break; // 为什么 i >= j 退出外循环？
            exch(a, i, j); // 小值放左边，大值放右边。
        }
        exch(a, lo, j);
        return j;
    }
}
```
快速排序最好的情况下是每次都正好能将数组对半分，这样递归调用次数才是最少的。

最坏的情况下，第一次从最小的元素切分，第二次从第二小的元素切分，如此这般。为了防止数组最开始就是有序的，在进行快速排序时需要随机打乱数组。

### 改进
1. 对于小数组，使用插入排序。只需将递归结束条件从 `if (hi <= lo) return;` 改为：`if (hi <= lo + 15) { new Insertion().sort(a); return; }`

2. 三取样切分：选取较优的切点元素来提高性能。将子数组的一小部分元素中的中位数作为切点来切分数组效果为好，一般取3个元素。

3. 对于含有大量重复元素的数组，该算法还是会继续切分数组，增加不必要的性能开销。解决方案：三向切分算法：[Quick3way.java](https://github.com/yuzh233/Algorithms/blob/master/src/chapter_2/_3quicksort/Quick3way.java)

以上改进 均未！实现！太搞脑子了😣~~~
   
    
