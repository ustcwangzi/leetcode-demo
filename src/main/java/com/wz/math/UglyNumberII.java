package com.wz.math;

import java.util.PriorityQueue;

/**
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *
 * Example:
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 *
 * Note:
 * 1. 1 is typically treated as an ugly number.
 * 2. n does not exceed 1690.
 */
public class UglyNumberII {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }

    /**
     * 使用最小堆，首先放进去 1，然后从 1 遍历到 n，每次取出堆顶元素，为了确保没有重复数字，进行一次 while 循环
     * 将此时和堆顶元素相同的都取出来，然后分别将堆顶元素乘以 2，3，5 并加入最小堆，这样最终堆顶元素就是第 n 个丑陋数
     */
    public static int nthUglyNumber(int n) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.offer(1L);
        for (int i = 1; i < n; i++) {
            long top = queue.poll();
            // 保证没有重复元素
            while (!queue.isEmpty() && queue.peek() == top) {
                top = queue.poll();
            }
            queue.offer(top * 2);
            queue.offer(top * 3);
            queue.offer(top * 5);
        }
        return queue.peek().intValue();
    }
}
