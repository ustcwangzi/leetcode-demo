package com.wz.queue;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given an array of non-negative integers nums and an integer k. In one operation, you may choose any element from nums and increment it by 1.
 * Return the maximum product of nums after at most k operations. Since the answer may be very large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: nums = [0,4], k = 5
 * Output: 20
 * Explanation: Increment the first number 5 times.
 * Now nums = [5, 4], with a product of 5 * 4 = 20.
 * It can be shown that 20 is maximum product possible, so we return 20.
 * Note that there may be other ways to increment nums to have the maximum product.
 *
 * Example 2:
 * Input: nums = [6,3,3,2], k = 2
 * Output: 216
 * Explanation: Increment the second number 1 time and increment the fourth number 1 time.
 * Now nums = [6, 4, 3, 3], with a product of 6 * 4 * 3 * 3 = 216.
 * It can be shown that 216 is maximum product possible, so we return 216.
 * Note that there may be other ways to increment nums to have the maximum product.
 *
 * Constraints:
 * 1. 1 <= nums.length, k <= 10^5
 * 2. 0 <= nums[i] <= 10^6
 */
public class MaximumProductAfterKIncrements {
    public static void main(String[] args) {
        System.out.println(maximumProduct(new int[]{0, 4}, 5));
        System.out.println(maximumProduct(new int[]{6, 3, 3, 2}, 2));
    }

    /**
     * 将较小的元素尽量变大，最终的乘积就是最大的
     * 先使用小根堆存储所有元素，再每次将堆顶元素加一后重新放回堆，最后对堆中全部元素计算乘积
     */
    public static int maximumProduct(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>(nums.length);
        Arrays.stream(nums).forEach(queue::offer);
        while (k-- > 0) {
            queue.offer(queue.poll() + 1);
        }

        int mod = 1_000_000_007;
        long product = 1;
        while (!queue.isEmpty()) {
            product = (product * queue.poll()) % mod;
        }
        return (int) product;
    }
}
