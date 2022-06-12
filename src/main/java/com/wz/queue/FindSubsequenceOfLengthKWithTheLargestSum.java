package com.wz.queue;

import java.util.*;

/**
 * You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.
 * Return any such subsequence as an integer array of length k.
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 * Input: nums = [2,1,3,3], k = 2
 * Output: [3,3]
 * Explanation:
 * The subsequence has the largest sum of 3 + 3 = 6.
 *
 * Example 2:
 * Input: nums = [-1,-2,3,4], k = 3
 * Output: [-1,3,4]
 * Explanation:
 * The subsequence has the largest sum of -1 + 3 + 4 = 6.
 *
 * Example 3:
 * Input: nums = [3,4,3,3], k = 2
 * Output: [3,4]
 * Explanation:
 * The subsequence has the largest sum of 3 + 4 = 7.
 * Another possible subsequence is [4, 3].
 *
 * Constraints:
 * 1. 1 <= nums.length <= 1000
 * 2. -10^5 <= nums[i] <= 10^5
 * 3. 1 <= k <= nums.length
 */
public class FindSubsequenceOfLengthKWithTheLargestSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSubsequence(new int[]{2, 1, 3, 3}, 2)));
        System.out.println(Arrays.toString(maxSubsequence(new int[]{-1, -2, 3, 4}, 3)));
    }

    /**
     * 就是要找到数组中最大的k个元素，与 {@link KthLargestElementInArray} 类似
     * 先用小根堆找到最大的k个元素，然后使用set记录这些元素的下标，再遍历数组将对应元素加入到结果集中
     */
    public static int[] maxSubsequence(int[] nums, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < nums.length; i++) {
            queue.offer(new int[]{nums[i], i});
            if (queue.size() > k) {
                queue.poll();
            }
        }

        Set<Integer> set = new HashSet<>(k);
        while (!queue.isEmpty()) {
            set.add(queue.poll()[1]);
        }

        int index = 0;
        int[] result = new int[k];
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(i)) {
                result[index++] = nums[i];
            }
        }
        return result;
    }
}
