package com.wz.lists;

/**
 * Today, the bookstore owner has a store open for customers.length minutes.  Every minute,
 * some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.
 * On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1,
 * otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.
 * The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.
 * Return the maximum number of customers that can be satisfied throughout the day.
 *
 * Example 1:
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * Output: 16
 * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
 * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 */
public class GrumpyBookstoreOwner {
    public static void main(String[] args) {
        int[] customers = new int[]{1, 0, 1, 2, 1, 1, 7, 5}, grumpy = new int[]{0, 1, 0, 1, 0, 1, 0, 1};
        System.out.println(maxSatisfied(customers, grumpy, 3));
    }

    /**
     * 每个时刻i会有customer[i]个顾客进店，在i时刻店主的情绪是grumpy[i]，若grumpy[i]==1则店主脾气暴躁，那么顾客会不满意，
     * 否则顾客讲感到满意，店主会一个技巧保证连续X时间内不暴躁，但他只能使用一次，最多可以有多少顾客满意
     *
     * 即统计在大小为 X 的窗口中, 最多有多少顾客处在店主脾气不好的时刻(grumpy[i] == 1).
     * 因为 grumpy[i] == 0 对应的顾客始终是满意的. 而对于那些 grumpy[i] == 1 的顾客, 只有在滑动窗口中, 才能满意
     *
     * 使用滑动窗口 windows 记录X分钟内不满意的客户数（X分钟），当滑动窗的宽度大于X时从滑动窗的左端减去不满意的客户
     * 使用 satisfied 记录店主脾气好的时候进来的客户数
     * 在迭代结束时，satisfied+ max(winOfMakeSatisfied)是答案
     */
    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int maxMakeSatisfied = 0, satisfied = 0, windows = 0;
        for (int i = 0; i < customers.length; ++i) {
            if (grumpy[i] == 0)
                satisfied += customers[i];
            else
                windows += customers[i];
            if (X <= i) {
                windows -= grumpy[i - X] * customers[i - X];
            }
            // 让窗口尽可能覆盖最多的不满意客户数
            maxMakeSatisfied = Math.max(maxMakeSatisfied, windows);
        }

        return satisfied + maxMakeSatisfied;
    }
}
