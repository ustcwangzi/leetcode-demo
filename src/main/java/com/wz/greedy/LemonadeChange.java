package com.wz.greedy;

/**
 * At a lemonade stand, each lemonade costs $5.
 * Customers are standing in a queue to buy from you, and order one at a time (in the order specified by bills).
 * Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.
 * You must provide the correct change to each customer, so that the net transaction is that the customer pays $5.
 * Note that you don't have any change in hand at first.
 * Return true if and only if you can provide every customer with correct change.
 *
 * Example 1:
 * Input: [5,5,5,10,20]
 * Output: true
 * Explanation:
 * From the first 3 customers, we collect three $5 bills in order.
 * From the fourth customer, we collect a $10 bill and give back a $5.
 * From the fifth customer, we give a $10 bill and a $5 bill.
 * Since all customers got correct change, we output true.
 *
 * Example 2:
 * Input: [5,5,10,10,20]
 * Output: false
 * Explanation:
 * From the first two customers in order, we collect two $5 bills.
 * For the next two customers in order, we collect a $10 bill and give back a $5 bill.
 * For the last customer, we can't give change of $15 back because we only have two $10 bills.
 * Since not every customer received correct change, the answer is false.
 *
 * Note:
 * 1. 0 <= bills.length <= 10000
 * 2. bills[i] will be either 5, 10, or 20.
 */
public class LemonadeChange {
    public static void main(String[] args) {
        System.out.println(lemonadeChange(new int[]{5, 5, 5, 10, 20}));
        System.out.println(lemonadeChange(new int[]{5, 5, 10, 10, 20}));
    }

    /**
     * 用 countFive、countTen 表示收到的 5 和10，因为不可能用 20 去找钱，就不用管了
     * 遍历数组，遇到 5 则直接 countFive++，遇到 10，就将 countFive--，同时 countTen++
     * 遇到 20，就有两种情况，要么是给一个 5 和一个 10，或者 3 个 5，当然，前一种比较好，因为 5 更灵活
     * 其中遇到 10 或 20 的时候需要判断已收到的钱是否够减的，不够则直接返回 false
     */
    public static boolean lemonadeChange(int[] bills) {
        int countFive = 0, countTen = 0;
        for (int bill : bills) {
            if (bill == 5) {
                countFive++;
                continue;
            }

            if (bill == 10) {
                if (countFive == 0) {
                    return false;
                }
                countTen++;
                countFive--;
                continue;
            }

            if (countFive > 0 && countTen > 0) {
                countFive--;
                countTen--;
            } else if (countFive > 3) {
                countFive -= 3;
            } else {
                return false;
            }
        }
        return true;
    }
}
