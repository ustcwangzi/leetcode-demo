package com.wz.array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray
 * such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 *
 * Example 1:
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 *
 * Example 2:
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 *
 * Example 3:
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 */
public class LongestSubarrayWithAbsoluteDiffLessThanOrEqualLimit {
    public static void main(String[] args) {
        int[] nums = new int[]{8, 2, 4, 7};
        System.out.println(longestSubarray(nums, 4));

        nums = new int[]{10, 1, 2, 4, 7, 2};
        System.out.println(longestSubarray(nums, 5));
    }

    /**
     * 滑动窗口
     * 用 left 和 right 维护一个滑动窗口，然后求滑动窗口内的最大值和最小值之差是否小于等于 limit
     * 用 maxDeque 和 minDeque 维护窗口内的最大值和最小值
     * 当最大值和最小值之差大于 limit 时，缩小滑动窗口，即 left 右移
     */
    public static int longestSubarray(int[] nums, int limit) {
        // 递减队列，队头为当前最大值
        Deque<Integer> maxDeque = new LinkedList<>();
        // 递增队列，队头为当前最小值
        Deque<Integer> minDeque = new LinkedList<>();

        int result = 1, left = 0;
        for (int right = 0; right < nums.length; right++) {
            // 移除小于当前值的队尾元素，保证队列递减
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < nums[right]) {
                maxDeque.removeLast();
            }
            maxDeque.addLast(nums[right]);

            // 移除大于当前值的队尾元素，保证队列递增
            while (!minDeque.isEmpty() && minDeque.peekLast() > nums[right]) {
                minDeque.removeLast();
            }
            minDeque.addLast(nums[right]);

            // 超过 limit 时，left 右移，同时把旧的 left 元素从队列中移除
            while (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
                if (maxDeque.peekFirst() == nums[left]) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == nums[left]) {
                    minDeque.pollFirst();
                }
                left++;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;
    }
}
