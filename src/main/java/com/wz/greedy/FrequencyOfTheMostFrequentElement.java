package com.wz.greedy;

import java.util.Arrays;

/**
 * The frequency of an element is the number of times it occurs in an array.
 * You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at that index by 1.
 * Return the maximum possible frequency of an element after performing at most k operations.
 *
 * Example 1:
 * Input: nums = [1,2,4], k = 5
 * Output: 3
 * Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
 * 4 has a frequency of 3.
 *
 * Example 2:
 * Input: nums = [1,4,8,13], k = 5
 * Output: 2
 * Explanation: There are multiple optimal solutions:
 * - Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
 * - Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
 * - Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^5
 * 3. 1 <= k <= 10^5
 */
public class FrequencyOfTheMostFrequentElement {
    public static void main(String[] args) {
        System.out.println(maxFrequency(new int[]{1, 2, 4}, 5));
        System.out.println(maxFrequency(new int[]{1, 4, 8, 13}, 5));
    }

    /**
     * 滑动窗口
     * 先对数组进行排序，然后使用一个滑动窗口 [left, right]，统计将 nums[left...right] 全部变成 nums[right] 需要的操作次数 count
     * 若 count > k，则说明需要的操作次数太多，将 left 右移，同时减少对应的操作次数
     * 否则，说明操作次数满足条件，将 right 右移，同时更新结果长度
     * 这里可以快速计算操作次数，以 [1, 2, 4] 为例说明该过程：
     * 初始时，left=0，right=1，窗口内元素为 [1,2]，需要变为[2,2]，操作次数为 nums[right] - nums[right-1]
     * 此时，left=0，right=2，窗口内元素为 [2,2,4]，需要变为[4,4,4]，操作次数为 (nums[right] - nums[right-1]) * (right-left)
     */
    public static int maxFrequency(int[] nums, int k) {
        Arrays.parallelSort(nums);

        int left = 0, right = 1, result = 1;
        long count = 0;
        while (right < nums.length) {
            // 操作次数
            count += (long) (nums[right] - nums[right - 1]) * (right - left);
            while (count > k) {
                count -= nums[right] - nums[left];
                left++;
            }
            result = Math.max(result, right - left + 1);
            right++;
        }

        return result;
    }
}
