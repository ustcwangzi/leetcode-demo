package com.wz.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given an array target of n integers. From a starting array arr consisting of n 1's, you may perform the following procedure :
 * - let x be the sum of all elements currently in your array.
 * - choose index i, such that 0 <= i < n and set the value of arr at index i to x.
 * - You may repeat this procedure as many times as needed.
 * Return true if it is possible to construct the target array from arr, otherwise, return false.
 *
 * Example 1:
 * Input: target = [9,3,5]
 * Output: true
 * Explanation: Start with arr = [1, 1, 1]
 * [1, 1, 1], sum = 3 choose index 1
 * [1, 3, 1], sum = 5 choose index 2
 * [1, 3, 5], sum = 9 choose index 0
 * [9, 3, 5] Done
 *
 * Example 2:
 * Input: target = [1,1,1,2]
 * Output: false
 * Explanation: Impossible to create target array from [1,1,1,1].
 *
 * Example 3:
 * Input: target = [8,5]
 * Output: true
 *
 * Constraints:
 * 1. n == target.length
 * 2. 1 <= n <= 5 * 10^4
 * 3. 1 <= target[i] <= 10^9
 */
public class ConstructTargetArrayWithMultipleSums {
    public static void main(String[] args) {
        System.out.println(isPossible(new int[]{9, 3, 5}));
        System.out.println(isPossible(new int[]{1, 1, 1, 2}));
    }

    /**
     * 倒推，从 target 开始，看能不能得到全 1 的数组，每次操作最大值，以 [3, 5, 33] 为例
     * 1. 最大值为 33，x = 33 % (3+5) = 1，得到 [3, 5, 1]
     * 2. 最大值为 5，x = 5 % (1+3) = 1，得到 [3, 1, 1]
     * 3. 最大值为 3，x = 3 % (1+1) = 1，得到 [1, 1, 1]
     */
    public static boolean isPossible(int[] target) {
        if (target.length == 1) {
            return target[0] == 1;
        }

        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        long sum = 0;
        for (int num : target) {
            sum += num;
            queue.offer(num);
        }

        while (queue.peek() != 1) {
            int cur = queue.poll();
            if (sum - cur == 1) {
                return true;
            }
            int x = (int) (cur % (sum - cur));
            sum = sum - cur + x;

            if (x == 0 || x == cur) {
                return false;
            }
            queue.offer(x);
        }
        return true;
    }
}
