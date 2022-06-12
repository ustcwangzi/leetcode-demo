package com.wz.queue;

import com.wz.twopointer.MinimumSizeSubArraySum;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k.
 * If there is no such subarray, return -1.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [1], k = 1
 * Output: 1
 *
 * Example 2:
 * Input: nums = [1,2], k = 4
 * Output: -1
 *
 * Example 3:
 * Input: nums = [2,-1,2], k = 3
 * Output: 3
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. -10^5 <= nums[i] <= 10^5
 * 3. 1 <= k <= 10^9
 */
public class ShortestSubarrayWithSumAtLeastK {
    public static void main(String[] args) {
        System.out.println(shortestSubarray(new int[]{2, -1, 2}, 3));
        System.out.println(shortestSubarray(new int[]{2, -1, 2, 1}, 3));
        System.out.println(shortestSubarray(new int[]{1}, 1));
        System.out.println(shortestSubarray(new int[]{1, 2}, 4));
    }

    /**
     * 单调队列
     * 是对 {@link MinimumSizeSubArraySum} 的扩展，{@link MinimumSizeSubArraySum} 规定了只有正数，本题允许负数
     * 使用单调队列保存累加和和对应下标，由于是求最小长度，若允许更小的累加和入队，则会导致结果变得更大，不符合要求
     * 遍历数组，对于每个 nums[i]，将其累加到 sum 中，若 sum-队首 >= k，说明找到了满足条件的子数组，更新结果
     * 若当前 sum <= 队尾，说明当前 sum 太小，需要将队尾移除，直到可以将 sum 入队，以 [2, -1, 2, 1], 3 说明该过程：
     * i    sum     result      queue
     *      0       -1          (0,-1)
     * 0    2       -1          (0,-1)(2,0)
     * 1    1       -1          (0,-1)(1,1) --移除(2,0)
     * 2    3       3           (1,1)(3,2)  --移除(0,-1)
     * 3    4       2           (3,2)(4,3)  --移除(1,1)
     * 如果没有保证队列的单调性，i==1 时，将不会移除(2,0)，结果变为：
     * i    sum     result      queue
     *      0       -1          (0,-1)
     * 0    2       -1          (0,-1)(2,0)
     * 1    1       -1          (0,-1)(2,0)(1,1)
     * 2    3       3           (2,0)(1,1)(3,2)  --移除(0,-1)
     * 3    4       3           (2,0)(1,1)(3,2)(4,3)
     */
    public static int shortestSubarray(int[] nums, int k) {
        long sum = 0, result = Integer.MAX_VALUE;
        Deque<long[]> deque = new LinkedList<>();
        deque.offer(new long[]{0, -1});
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (!deque.isEmpty() && sum - deque.peekFirst()[0] >= k) {
                result = Math.min(result, i - deque.pollFirst()[1]);
            }
            while (!deque.isEmpty() && sum <= deque.peekLast()[0]) {
                deque.pollLast();
            }
            deque.offerLast(new long[]{sum, i});
        }
        return (int) (result == Integer.MAX_VALUE ? -1 : result);
    }
}
