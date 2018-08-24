package chapter_2._4priority_queues;

import java.util.Date;

/**
 * @Author: yu_zh
 * @DateTime: 2018/08/24 15:49
 */
public class Transaction implements Comparable<Transaction> {
    private String who;
    private Date when;
    private double amount;

    Transaction(String transaction) {
        String[] a = transaction.split("\\s+");
        who = a[0];
        when = new Date(a[1]);
        amount = Double.parseDouble(a[2]);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "who='" + who + '\'' +
                ", when=" + when +
                ", amount=" + amount +
                '}';
    }

    @Override
    public int compareTo(Transaction o) {
        if (this.amount > o.amount) return 1;
        if (this.amount < o.amount) return -1;
        return 0;
    }
}
